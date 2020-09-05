package com.example.newkorona.presentation

import androidx.lifecycle.*
import com.example.newkorona.ApiFactory
import com.example.newkorona.ApiService
import com.example.newkorona.Country
import com.example.newkorona.filter.CountriesListFilter
import com.example.newkorona.filter.CountriesListFilterImpl
import com.example.newkorona.repository.CountriesRepository
import com.example.newkorona.repository.CountriesRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MyViewModel(private val countryRepository : CountriesRepository, private val countryListFilter: CountriesListFilter): ViewModel() {

    val countryData: LiveData<List<Country>> = LiveDataReactiveStreams.fromPublisher(
        countryRepository
            .fetchAllCountriesSingle()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toFlowable()
    )


    var queryLiveData: MutableLiveData<String> = MutableLiveData("") //Stumień wejściowy

    var filteredCountryData: LiveData<List<Country>> = Transformations.switchMap(queryLiveData, { //switchMap mapowanie do Livedata, map służy do mapowania danych wewnątrz LiveData
            query -> countryData.map {
        countryListFilter.filter(it ,query) }
    })

    fun dataFilter(query:String){ //przekazanie nowej wartości na strumień wejściowy
        queryLiveData.value = query
    }
}
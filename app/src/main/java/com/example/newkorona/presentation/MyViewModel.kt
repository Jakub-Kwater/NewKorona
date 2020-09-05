package com.example.newkorona.presentation


import androidx.lifecycle.*
import com.example.newkorona.Country
import com.example.newkorona.filter.CountriesListFilter
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.newkorona.repository.CountriesRepository
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
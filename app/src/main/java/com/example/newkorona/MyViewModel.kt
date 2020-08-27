package com.example.newkorona

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newkorona.repository.CountriesRepository
import com.example.newkorona.repository.CountriesRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Publisher

class MyViewModel: ViewModel() {
    private val api:ApiService = ApiFactory.create()
    private val countryRepository : CountriesRepository? = CountriesRepositoryImpl(api = api)

    private var countryList: List<Country> = emptyList()
    private lateinit var dataBase: AppDatabase


    fun getCountries(): LiveData<List<Country>> {
        loadCountries()
        val countries:LiveData<List<Country>> = LiveDataReactiveStreams.fromPublisher(Publisher {  countryRepository?.fetchAllCountriesSingle()})
        return countries
    }

    private fun loadCountries() {


        countryRepository
            ?.fetchAllCountriesSingle()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.onErrorReturnItem(
                CountryEntityToCountryMapping.create(dataBase.countryDAO().getAll())
            )
            ?.subscribe({
                countryList = it
                val countryEntityList = CountryToCountryEntityMapping.create(countryList)

                dataBase.countryDAO()
                    .insertAll(countryEntityList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe() // I don't know why would I remove this line, yup that one must stay for without

                dataBase.countryDAO().delete(dataBase.countryDAO().getAll())
                dataBase.countryDAO().insertAll(countryEntityList)
            },{

            })


    }

}
package com.example.newkorona.repository

import com.example.newkorona.*
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class CountriesRepositoryImpl(
    private val api: ApiService,
    private val appDatabase: AppDatabase) :
    CountriesRepository {

    private val dao = appDatabase.countryDAO()

    override fun fetchAllCountriesSingle(): Single<List<Country>> {
        return api
            .fetchAllCountriesSingle()
            .doOnSuccess { countryList ->
                dao.delete(dao.getAll())
                dao.insertAll(CountryToCountryEntityMapping.create(countryList))
            }
            .onErrorReturnItem(CountryEntityToCountryMapping.create(dao.getAll()))
    }
}
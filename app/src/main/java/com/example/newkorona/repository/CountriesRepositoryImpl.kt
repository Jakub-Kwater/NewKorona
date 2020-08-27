package com.example.newkorona.repository

import com.example.newkorona.ApiService
import com.example.newkorona.Country
import io.reactivex.Single

class CountriesRepositoryImpl(private val api:ApiService) : CountriesRepository {

    override fun fetchAllCountriesSingle(): Single<List<Country>> {
        return api.fetchAllCountriesSingle()
    }
}
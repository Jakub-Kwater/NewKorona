package com.example.newkorona.repository

import com.example.newkorona.Country
import io.reactivex.Single

interface CountriesRepository {

    fun fetchAllCountriesSingle(): Single<List<Country>>

}
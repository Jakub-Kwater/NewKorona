package com.example.newkorona.repository

import com.example.newkorona.Country
import io.reactivex.Single

interface CountriesRepository {

//    fun fetchAllCountries(onCountriesFetched: (List<Country>) -> Unit)

    fun fetchAllCountriesSingle(): Single<List<Country>>
//    fun fetchAllCountriesSingle(onCountriesFetched: (List<Country>) -> Unit)


}
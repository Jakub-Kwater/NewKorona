package com.example.newkorona.repository

import com.example.newkorona.Country

interface CountriesRepository {

    fun fetchAllCountries(onCountriesFetched: (List<Country>) -> Unit)

}
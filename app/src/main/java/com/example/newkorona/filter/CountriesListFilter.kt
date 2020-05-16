package com.example.newkorona.filter

import com.example.newkorona.Country

interface CountriesListFilter {

    fun filter(countriesList: List<Country>, query: String): List<Country>
}
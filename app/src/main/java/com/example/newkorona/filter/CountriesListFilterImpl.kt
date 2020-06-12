package com.example.newkorona.filter

import com.example.newkorona.Country

class CountriesListFilterImpl : CountriesListFilter {

    override fun filter(countriesList: List<Country>, query: String): List<Country> {
        return countriesList.filter { country -> country.country.contains(query, ignoreCase = true) }
    }

}

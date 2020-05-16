package com.example.newkorona.filter

import com.example.newkorona.Country

class CountriesListFilterImpl : CountriesListFilter {

    override fun filter(countriesList: List<Country>, query: String): List<Country> {
        return countriesList.filter { country -> country.country.startsWith(query, ignoreCase = true) }
    }

<<<<<<< HEAD
}
=======
}
>>>>>>> 548c4c6a0132c2b9579367f961b4e08c480c01dc

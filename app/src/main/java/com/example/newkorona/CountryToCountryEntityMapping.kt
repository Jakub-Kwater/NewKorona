package com.example.newkorona

object CountryToCountryEntityMapping {
    fun create(countries: List<Country>): List<CountryEntity> = countries.map {  country -> CountryEntity(
        country = country.country,
        cases = country.cases,
        todayCases = country.todayCases,
        deaths = country.deaths,
        todayDeaths = country.todayDeaths,
        recovered = country.recovered,
        active = country.active,
        casesPerOneMillion = country.casesPerOneMillion,
        totalTests = country.totalTests,
        testsPerOneMillion = country.testsPerOneMillion,
        deathsPerOneMillion = country.deathsPerOneMillion,
        critical = country.critical
    )
    }
}
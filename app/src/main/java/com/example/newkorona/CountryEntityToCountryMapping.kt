package com.example.newkorona

object CountryEntityToCountryMapping{
    fun create(countryEntities: List<CountryEntity>): List<Country> =  countryEntities.map { CountryEntity -> Country(
        country = CountryEntity.country,
        cases = CountryEntity.cases,
        todayCases = CountryEntity.todayCases,
        deaths = CountryEntity.deaths,
        todayDeaths = CountryEntity.todayDeaths,
        recovered = CountryEntity.recovered,
        active = CountryEntity.active,
        casesPerOneMillion = CountryEntity.casesPerOneMillion,
        totalTests = CountryEntity.totalTests,
        testsPerOneMillion = CountryEntity.testsPerOneMillion,
        deathsPerOneMillion = CountryEntity.deathsPerOneMillion,
        critical = CountryEntity.critical
        )
    }
}
package com.example.newkorona

data class Country(
    val country: String,
    val cases: Int,
    val todayCases: Int,
    val deaths: Int,
    val todayDeaths: Int,
    val recovered: Int,
    val active: Int,
    val critical: Int,
    val casesPerOneMillion: Int,
    val deathsPerOneMillion: Int,
    val totalTests: Int,
    val testsPerOneMillion: Int
)
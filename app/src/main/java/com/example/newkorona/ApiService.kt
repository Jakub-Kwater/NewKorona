package com.example.newkorona

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/countries")
    fun fetchAllCountries(): Call<List<Country>>
}
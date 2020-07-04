package com.example.newkorona

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/countries")
    fun fetchAllCountries(): Call<List<Country>>

    @GET("/countries")
    fun fetchAllCountriesSingle(): Single<List<Country>>
}
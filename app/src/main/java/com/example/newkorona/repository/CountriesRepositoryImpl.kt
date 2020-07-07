package com.example.newkorona.repository

import android.util.Log
import com.example.newkorona.ApiService
import com.example.newkorona.Country
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountriesRepositoryImpl(private val api:ApiService) : CountriesRepository {

    override fun fetchAllCountriesSingle(): Single<List<Country>> {
        return api.fetchAllCountriesSingle()
    }
}
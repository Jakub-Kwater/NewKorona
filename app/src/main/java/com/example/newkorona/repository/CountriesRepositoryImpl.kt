package com.example.newkorona.repository

import android.util.Log
import com.example.newkorona.ApiService
import com.example.newkorona.Country
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountriesRepositoryImpl : CountriesRepository {

    val retrofit = Retrofit.Builder()
        .baseUrl("http://coronavirus-19-api.herokuapp.com/countries/?fbclid=IwAR0wZuXrAzDdY6q8rTQGLmun_-l6ZLVv6MLP8h67JC3Q2aHf8mVPRNpyNpU")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(ApiService::class.java)

    override fun fetchAllCountries(onCountriesFetched: (List<Country>) -> Unit) {
        api.fetchAllCountries().enqueue(object : Callback<List<Country>> {
            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                Log.d("TAG_KORONA", "error" + t.message)
            }

            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                response.body()?.let { newCountryList -> onCountriesFetched(newCountryList) }
                Log.d("TAG_KORONA", "ok")
                Log.d("TAG_KORONA", "List  size: " + response.body()!![2].country)

            }
        })
    }
}
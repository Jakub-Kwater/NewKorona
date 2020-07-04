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

//    override fun fetchAllCountries(onCountriesFetched: (List<Country>) -> Unit) {
//
//        api.fetchAllCountries().enqueue(object : Callback<List<Country>> {
//            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
//                Log.d("TAG_KORONA", "error" + t.message)
//            }
//
//            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
//                response.body()?.let { newCountryList -> onCountriesFetched(newCountryList) }
//                Log.d("TAG_KORONA", "ok")
//                Log.d("TAG_KORONA", "List  size: " + response.body()!![2].country)
//
//            }
//        })
//    }

    override fun fetchAllCountriesSingle(): Single<List<Country>> {
        return api.fetchAllCountriesSingle()
    }
}
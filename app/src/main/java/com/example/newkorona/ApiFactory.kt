package com.example.newkorona

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    val retrofit = Retrofit.Builder()
        .baseUrl("http://coronavirus-19-api.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    fun create(): ApiService = retrofit.create(ApiService::class.java)
}
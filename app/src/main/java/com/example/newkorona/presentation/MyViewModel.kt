package com.example.newkorona.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.newkorona.ApiFactory
import com.example.newkorona.ApiService
import com.example.newkorona.Country
import com.example.newkorona.repository.CountriesRepository
import com.example.newkorona.repository.CountriesRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MyViewModel(private val countryRepository : CountriesRepository): ViewModel() {

    val countryData: LiveData<List<Country>> = LiveDataReactiveStreams.fromPublisher(
        countryRepository
            .fetchAllCountriesSingle()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toFlowable()
    )
}
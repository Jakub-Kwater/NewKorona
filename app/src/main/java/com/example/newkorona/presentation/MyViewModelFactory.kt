package com.example.newkorona.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newkorona.filter.CountriesListFilter
import com.example.newkorona.repository.CountriesRepository
import java.lang.IllegalArgumentException

class MyViewModelFactory(private val countryRepository : CountriesRepository, private val countryListFilter: CountriesListFilter) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MyViewModel::class.java)) {
            return MyViewModel(countryRepository, countryListFilter) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}
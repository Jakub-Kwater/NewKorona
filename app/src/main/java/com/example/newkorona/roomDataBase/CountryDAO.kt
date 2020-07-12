package com.example.newkorona.roomDataBase

import androidx.room.Query
import com.example.newkorona.CountryEntity
import io.reactivex.Single

interface CountryDAO {
    @Query("SELECT * FROM countryentity")
    fun getAll(): Single<List<CountryEntity>>
}
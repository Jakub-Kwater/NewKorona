package com.example.newkorona.roomDataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.newkorona.CountryEntity
import io.reactivex.Single

@Dao
interface CountryDAO {
    @Query("SELECT * FROM countryEntity")
    fun getAll(): List<CountryEntity>

    @Insert
    fun insertAll(countries: List<CountryEntity>)

    @Insert
    fun insertCountry(country: CountryEntity)

    @Delete
    fun delete(country: CountryEntity)
}
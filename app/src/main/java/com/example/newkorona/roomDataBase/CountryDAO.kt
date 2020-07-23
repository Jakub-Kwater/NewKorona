package com.example.newkorona.roomDataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.newkorona.Country
import com.example.newkorona.CountryEntity
import io.reactivex.Completable

@Dao
interface CountryDAO {
    @Query("SELECT * FROM countryEntity")
    fun getAll(): List<CountryEntity>

    @Insert
    fun insertAll(countries: List<CountryEntity>) : Completable

    @Insert
    fun insertCountry(country: CountryEntity)

    @Delete
    fun delete(country: List<CountryEntity>)

}
package com.example.newkorona.roomDataBase

import androidx.room.*
import com.example.newkorona.Country
import com.example.newkorona.CountryEntity
import io.reactivex.Completable

@Dao
interface CountryDAO {
    @Query("SELECT * FROM countryEntity")
    fun getAll(): List<CountryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(countries: List<CountryEntity>)

    @Insert
    fun insertCountry(country: CountryEntity)

    @Delete
    fun delete(country: List<CountryEntity>)

}
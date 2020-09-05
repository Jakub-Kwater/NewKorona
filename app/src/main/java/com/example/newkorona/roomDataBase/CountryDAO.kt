package com.example.newkorona.roomDataBase

import androidx.room.*

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
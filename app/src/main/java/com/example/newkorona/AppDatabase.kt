package com.example.newkorona

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newkorona.roomDataBase.CountryDAO
import com.example.newkorona.roomDataBase.CountryEntity


@Database(entities = [CountryEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun countryDAO(): CountryDAO
}
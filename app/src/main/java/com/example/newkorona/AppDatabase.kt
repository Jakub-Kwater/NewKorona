package com.example.newkorona

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newkorona.roomDataBase.CountryDAO


@Database(entities = [CountryEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun countryDAO(): CountryDAO
}
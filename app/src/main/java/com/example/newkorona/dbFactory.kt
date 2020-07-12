package com.example.newkorona

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newkorona.roomDataBase.CountryDAO


}

object dbFactory {
    @Database(entities = arrayOf(CountryEntity::class), version = 1)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun countryDAO(): CountryDAO

    val db = Room.databaseBuilder(
        context = ,
        AppDatabase::class.java, "database-name"
    ).build())
}
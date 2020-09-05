package com.example.newkorona.roomDataBase

import android.content.Context
import androidx.room.Room
import com.example.newkorona.AppDatabase

object dbFactory {
    fun create(context:Context): AppDatabase = Room.databaseBuilder(context,
        AppDatabase::class.java,"country_database").allowMainThreadQueries().build()
}
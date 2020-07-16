package com.example.newkorona

import android.content.Context
import androidx.room.Room

object dbFactory {
    fun create(context:Context): AppDatabase = Room.databaseBuilder(context,AppDatabase::class.java,"name").build()
}
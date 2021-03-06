package com.example.newkorona.roomDataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CountryEntity (
    @PrimaryKey(autoGenerate = true) val cid: Long = 0,
    @ColumnInfo(name = "Country Name") val country: String,
    @ColumnInfo(name = "Cases") val cases: Int,
    @ColumnInfo(name = "Today Cases") val todayCases: Int,
    @ColumnInfo(name = "Deaths") val deaths: Int,
    @ColumnInfo(name = "Today Deaths") val todayDeaths: Int,
    @ColumnInfo(name = "Recovered") val recovered: Int,
    @ColumnInfo(name = "Active") val active: Int,
    @ColumnInfo(name = "Critical") val critical: Int,
    @ColumnInfo(name = "Cases Per One Million") val casesPerOneMillion: Int,
    @ColumnInfo(name = "Deaths Per One Million") val deathsPerOneMillion: Int,
    @ColumnInfo(name = "Total Tests") val totalTests: Int,
    @ColumnInfo(name = "Tests Per One Million") val testsPerOneMillion: Int
)
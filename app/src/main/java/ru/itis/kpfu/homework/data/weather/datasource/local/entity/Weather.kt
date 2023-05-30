package ru.itis.kpfu.homework.data.weather.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class Weather(
    @PrimaryKey val name: String,
    val icon: String?,
    val temperature: Double,
    val windDegree: Int,
    val humidity: Int,
    val lat: Double?,
    val lon: Double?,
)

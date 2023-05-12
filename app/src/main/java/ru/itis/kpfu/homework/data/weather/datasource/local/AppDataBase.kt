package ru.itis.kpfu.homework.data.weather.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.itis.kpfu.homework.data.weather.datasource.local.dao.WeatherDao
import ru.itis.kpfu.homework.data.weather.datasource.local.entity.Weather

@Database(entities = [Weather::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun getWeatherDao(): WeatherDao
}

package ru.itis.kpfu.homework.data.weather.datasource.local

import android.content.Context
import androidx.room.Room
import ru.itis.kpfu.homework.data.weather.datasource.local.entity.Weather

class WeatherRepository(private val context: Context) {

    private val db by lazy {
        Room.databaseBuilder(context, AppDataBase::class.java, DATABASE_NAME)
            .build()
    }

    private val weatherDao by lazy {
        db.getWeatherDao()
    }

    suspend fun saveWeather(weather: Weather) {
        weatherDao.save(weather)
    }

    suspend fun deleteWeather(weather: Weather) {
        weatherDao.delete(weather)
    }

    suspend fun findByName(name: String): Weather? = weatherDao.findByName(name)

    companion object {
        const val DATABASE_NAME = "weather.db"
    }

}

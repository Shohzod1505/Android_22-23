package ru.itis.kpfu.homework.data

import android.content.Context
import androidx.room.Room
import ru.itis.kpfu.homework.data.weather.datasource.remote.WeatherApi
import ru.itis.kpfu.homework.data.weather.mapper.toWeatherInfo
import ru.itis.kpfu.homework.domain.weather.WeatherInfo
import ru.itis.kpfu.homework.domain.weather.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherApi: WeatherApi
): WeatherRepository {

    override suspend fun getWeatherByName(
        query: String?
    ): WeatherInfo = weatherApi.getWeather(query).toWeatherInfo()

    override suspend fun getWeatherByCoord(
        lat: Double?, lon: Double?
    ): WeatherInfo = weatherApi.getWeather(lat, lon).toWeatherInfo()

}

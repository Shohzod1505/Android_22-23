package ru.itis.kpfu.homework.data.weather

import ru.itis.kpfu.homework.data.weather.datasource.remote.WeatherApi
import ru.itis.kpfu.homework.data.weather.mapper.toWeatherInfo
import ru.itis.kpfu.homework.domain.weather.WeatherInfo
import ru.itis.kpfu.homework.domain.weather.WeatherApiRepository

class WeatherApiRepositoryImpl(
    private val weatherApi: WeatherApi
): WeatherApiRepository {

    override suspend fun getWeatherByName(
        query: String?
    ): WeatherInfo = weatherApi.getWeather(query).toWeatherInfo()

    override suspend fun getWeatherByCoord(
        lat: Double?, lon: Double?
    ): WeatherInfo = weatherApi.getWeather(lat, lon).toWeatherInfo()

}

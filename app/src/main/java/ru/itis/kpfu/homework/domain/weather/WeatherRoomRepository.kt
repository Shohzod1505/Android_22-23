package ru.itis.kpfu.homework.domain.weather

import ru.itis.kpfu.homework.data.weather.datasource.local.entity.Weather

interface WeatherRoomRepository {

    suspend fun saveWeatherUseCase(weather: Weather)

    suspend fun deleteWeatherUseCase(weather: Weather)

    suspend fun findWeatherByNameUseCase(name: String): Weather?

}

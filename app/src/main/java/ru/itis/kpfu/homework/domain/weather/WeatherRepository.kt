package ru.itis.kpfu.homework.domain.weather

interface WeatherRepository {

    suspend fun getWeatherByName(query: String?): WeatherInfo

    suspend fun getWeatherByCoord(lat: Double?, lon: Double?): WeatherInfo


}

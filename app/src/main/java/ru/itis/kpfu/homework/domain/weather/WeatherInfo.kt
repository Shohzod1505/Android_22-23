package ru.itis.kpfu.homework.domain.weather

data class WeatherInfo(
    val name: String,
    val icon: String?,
    val temperature: Double,
    val windDegree: Int,
    val humidity: Int,
    val lat: Double?,
    val lon: Double?,
)

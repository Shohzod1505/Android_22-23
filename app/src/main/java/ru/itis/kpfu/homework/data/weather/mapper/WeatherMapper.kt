package ru.itis.kpfu.homework.data.weather.mapper

import ru.itis.kpfu.homework.data.weather.datasource.local.entity.Weather
import ru.itis.kpfu.homework.data.weather.datasource.remote.response.WeatherResponse
import ru.itis.kpfu.homework.domain.weather.WeatherInfo

fun WeatherResponse.toWeatherInfo(): WeatherInfo = WeatherInfo(
    name = name,
    icon = weather.firstOrNull()?.icon,
    temperature = main.temp,
    windDegree = wind.deg,
    humidity = main.humidity,
    lat = coord?.lat,
    lon = coord?.lon,
)

fun List<WeatherResponse>.toListWeatherInfo(): List<WeatherInfo> = map {
    it.toWeatherInfo()
}

fun WeatherInfo.toWeather() = Weather(
    name = name,
    icon = icon,
    temperature = temperature,
    windDegree = windDegree,
    humidity = humidity,
    lat = lat,
    lon = lon,
)

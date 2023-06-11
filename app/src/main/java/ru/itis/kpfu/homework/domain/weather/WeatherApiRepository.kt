package ru.itis.kpfu.homework.domain.weather

import io.reactivex.rxjava3.core.Single

interface WeatherApiRepository {

    fun getWeatherByName(query: String?): Single<WeatherInfo>

    fun getWeatherByCoord(lat: Double?, lon: Double?): Single<WeatherInfo>

}

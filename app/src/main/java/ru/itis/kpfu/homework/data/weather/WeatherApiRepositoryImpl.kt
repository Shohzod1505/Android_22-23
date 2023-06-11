package ru.itis.kpfu.homework.data.weather

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.itis.kpfu.homework.data.weather.datasource.remote.WeatherApi
import ru.itis.kpfu.homework.data.weather.mapper.toWeatherInfo
import ru.itis.kpfu.homework.domain.weather.WeatherInfo
import ru.itis.kpfu.homework.domain.weather.WeatherApiRepository

class WeatherApiRepositoryImpl(
    private val weatherApi: WeatherApi
): WeatherApiRepository {

    override fun getWeatherByName(
        query: String?
    ): Single<WeatherInfo> = weatherApi.getWeather(query)
        .observeOn(Schedulers.computation())
        .map { it.toWeatherInfo() }
        .subscribeOn(Schedulers.io())

    override fun getWeatherByCoord(
        lat: Double?, lon: Double?
    ): Single<WeatherInfo> = weatherApi.getWeather(lat, lon)
        .observeOn(Schedulers.computation())
        .map { it.toWeatherInfo() }
        .subscribeOn(Schedulers.io())

}

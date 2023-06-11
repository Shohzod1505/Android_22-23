package ru.itis.kpfu.homework.domain.weather

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.itis.kpfu.homework.data.weather.datasource.local.entity.Weather

interface WeatherRoomRepository {

    fun saveWeatherUseCase(weather: Weather): Completable

    fun deleteWeatherUseCase(weather: Weather): Completable

    fun findWeatherByNameUseCase(name: String): Single<Weather>

}

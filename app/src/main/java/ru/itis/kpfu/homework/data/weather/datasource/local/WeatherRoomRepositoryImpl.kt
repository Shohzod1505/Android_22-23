package ru.itis.kpfu.homework.data.weather.datasource.local

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.itis.kpfu.homework.data.weather.datasource.local.dao.WeatherDao
import ru.itis.kpfu.homework.data.weather.datasource.local.entity.Weather
import ru.itis.kpfu.homework.data.weather.mapper.toWeatherInfo
import ru.itis.kpfu.homework.domain.weather.WeatherRoomRepository

class WeatherRoomRepositoryImpl(
    private val weatherDao: WeatherDao
): WeatherRoomRepository  {

    override fun saveWeatherUseCase(
        weather: Weather
    ): Completable = weatherDao.save(weather)
        .subscribeOn(Schedulers.io())

    override fun deleteWeatherUseCase(
        weather: Weather
    ): Completable = weatherDao.delete(weather)
        .subscribeOn(Schedulers.io())

    override fun findWeatherByNameUseCase(
        name: String
    ): Single<Weather> = weatherDao.findByName(name)
        .subscribeOn(Schedulers.io())

}

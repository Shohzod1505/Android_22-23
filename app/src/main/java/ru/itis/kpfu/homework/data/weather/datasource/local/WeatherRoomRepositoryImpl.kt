package ru.itis.kpfu.homework.data.weather.datasource.local

import ru.itis.kpfu.homework.data.weather.datasource.local.dao.WeatherDao
import ru.itis.kpfu.homework.data.weather.datasource.local.entity.Weather
import ru.itis.kpfu.homework.domain.weather.WeatherRoomRepository

class WeatherRoomRepositoryImpl(
    private val weatherDao: WeatherDao
): WeatherRoomRepository  {

    override suspend fun saveWeatherUseCase(weather: Weather) {
        weatherDao.save(weather)
    }

    override suspend fun deleteWeatherUseCase(weather: Weather) {
        weatherDao.delete(weather)
    }

    override suspend fun findWeatherByNameUseCase(name: String): Weather? {
        return weatherDao.findByName(name)
    }

}

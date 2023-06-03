package ru.itis.kpfu.homework.domain.weather

import ru.itis.kpfu.homework.data.weather.datasource.local.entity.Weather

class SaveWeatherUseCase(
    private val weatherRoomRepository: WeatherRoomRepository
) {
    suspend operator fun invoke(weather: Weather) = weatherRoomRepository.saveWeatherUseCase(weather)
}

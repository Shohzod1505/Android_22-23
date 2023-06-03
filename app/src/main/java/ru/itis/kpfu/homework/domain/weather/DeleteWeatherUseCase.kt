package ru.itis.kpfu.homework.domain.weather

import ru.itis.kpfu.homework.data.weather.datasource.local.entity.Weather

class DeleteWeatherUseCase(
    private val weatherRoomRepository: WeatherRoomRepository
) {
    suspend operator fun invoke(
        weather: Weather
    ) = weatherRoomRepository.deleteWeatherUseCase(weather)
}

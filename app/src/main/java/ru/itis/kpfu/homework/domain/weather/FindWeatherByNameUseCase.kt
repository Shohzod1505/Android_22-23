package ru.itis.kpfu.homework.domain.weather

import ru.itis.kpfu.homework.data.weather.datasource.local.entity.Weather

class FindWeatherByNameUseCase(
    private val weatherRoomRepository: WeatherRoomRepository
) {
    suspend operator fun invoke(
        name: String
    ): Weather? = weatherRoomRepository.findWeatherByNameUseCase(name)
}

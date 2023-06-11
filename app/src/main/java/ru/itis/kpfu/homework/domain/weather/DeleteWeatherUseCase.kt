package ru.itis.kpfu.homework.domain.weather

import io.reactivex.rxjava3.core.Completable
import ru.itis.kpfu.homework.data.weather.datasource.local.entity.Weather

class DeleteWeatherUseCase(
    private val weatherRoomRepository: WeatherRoomRepository
) {
    operator fun invoke(
        weather: Weather
    ): Completable = weatherRoomRepository.deleteWeatherUseCase(weather)
}

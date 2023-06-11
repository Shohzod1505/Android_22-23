package ru.itis.kpfu.homework.domain.weather

import io.reactivex.rxjava3.core.Single
import ru.itis.kpfu.homework.data.weather.datasource.local.entity.Weather

class FindWeatherByNameUseCase(
    private val weatherRoomRepository: WeatherRoomRepository
) {
    operator fun invoke(
        name: String
    ): Single<Weather> = weatherRoomRepository.findWeatherByNameUseCase(name)
}

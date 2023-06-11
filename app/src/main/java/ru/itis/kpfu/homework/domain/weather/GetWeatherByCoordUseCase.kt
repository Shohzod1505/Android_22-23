package ru.itis.kpfu.homework.domain.weather

import io.reactivex.rxjava3.core.Single

class GetWeatherByCoordUseCase(
    private val weatherApiRepository: WeatherApiRepository
) {
    operator fun invoke(
        lat: Double?,
        lon: Double?
    ): Single<WeatherInfo> = weatherApiRepository.getWeatherByCoord(lat, lon)
}

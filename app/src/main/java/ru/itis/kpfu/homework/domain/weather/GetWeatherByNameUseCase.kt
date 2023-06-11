package ru.itis.kpfu.homework.domain.weather

import io.reactivex.rxjava3.core.Single

class GetWeatherByNameUseCase(
    private val weatherApiRepository: WeatherApiRepository
) {
    operator fun invoke(
        query: String?
    ): Single<WeatherInfo> = weatherApiRepository.getWeatherByName(query)
}

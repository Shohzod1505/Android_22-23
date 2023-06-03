package ru.itis.kpfu.homework.domain.weather

class GetWeatherByNameUseCase(
    private val weatherApiRepository: WeatherApiRepository
) {
    suspend operator fun invoke(query: String?): WeatherInfo = weatherApiRepository.getWeatherByName(query)
}

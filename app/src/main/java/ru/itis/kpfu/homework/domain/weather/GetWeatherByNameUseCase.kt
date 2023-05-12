package ru.itis.kpfu.homework.domain.weather

class GetWeatherByNameUseCase(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(query: String?): WeatherInfo = weatherRepository.getWeatherByName(query)
}

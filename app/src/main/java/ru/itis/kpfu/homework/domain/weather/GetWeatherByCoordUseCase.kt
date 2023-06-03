package ru.itis.kpfu.homework.domain.weather

class GetWeatherByCoordUseCase(
    private val weatherApiRepository: WeatherApiRepository
) {
    suspend operator fun invoke(lat: Double?, lon: Double?): WeatherInfo = weatherApiRepository.getWeatherByCoord(lat, lon)
}

package ru.itis.kpfu.homework.domain.weather

class GetWeatherByCoordUseCase(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(lat: Double?, lon: Double?): WeatherInfo = weatherRepository.getWeatherByCoord(lat, lon)
}

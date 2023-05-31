package ru.itis.kpfu.homework.di

import dagger.Module
import dagger.Provides
import ru.itis.kpfu.homework.data.weather.WeatherRepositoryImpl
import ru.itis.kpfu.homework.data.weather.datasource.remote.WeatherApi
import ru.itis.kpfu.homework.domain.weather.GetWeatherByCoordUseCase
import ru.itis.kpfu.homework.domain.weather.GetWeatherByNameUseCase
import ru.itis.kpfu.homework.domain.weather.WeatherRepository

@Module
class WeatherModule {

    @Provides
    fun provideWeatherRepository(
        weatherApi: WeatherApi
    ): WeatherRepository = WeatherRepositoryImpl(weatherApi)

    @Provides
    fun provideWeatherByNameUseCase(
        repository: WeatherRepository
    ): GetWeatherByNameUseCase = GetWeatherByNameUseCase(repository)

    @Provides
    fun provideWeatherByCoordUseCase(
        repository: WeatherRepository
    ): GetWeatherByCoordUseCase = GetWeatherByCoordUseCase(repository)

}

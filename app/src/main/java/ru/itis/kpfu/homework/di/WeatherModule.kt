package ru.itis.kpfu.homework.di

import dagger.Module
import dagger.Provides
import ru.itis.kpfu.homework.data.weather.WeatherApiRepositoryImpl
import ru.itis.kpfu.homework.data.weather.datasource.local.WeatherRoomRepositoryImpl
import ru.itis.kpfu.homework.data.weather.datasource.local.dao.WeatherDao
import ru.itis.kpfu.homework.data.weather.datasource.remote.WeatherApi
import ru.itis.kpfu.homework.domain.weather.*

@Module
class WeatherModule {

    @Provides
    fun provideWeatherRepository(
        weatherApi: WeatherApi
    ): WeatherApiRepository = WeatherApiRepositoryImpl(weatherApi)

    @Provides
    fun provideWeatherRoomRepository(
        weatherDao: WeatherDao
    ): WeatherRoomRepository = WeatherRoomRepositoryImpl(weatherDao)

    @Provides
    fun provideWeatherByNameUseCase(
        repository: WeatherApiRepository
    ): GetWeatherByNameUseCase = GetWeatherByNameUseCase(repository)

    @Provides
    fun provideWeatherByCoordUseCase(
        repository: WeatherApiRepository
    ): GetWeatherByCoordUseCase = GetWeatherByCoordUseCase(repository)

    @Provides
    fun provideSaveWeatherUseCase(
        repository: WeatherRoomRepository
    ): SaveWeatherUseCase = SaveWeatherUseCase(repository)

    @Provides
    fun provideDeleteWeatherUseCase(
        repository: WeatherRoomRepository
    ): DeleteWeatherUseCase = DeleteWeatherUseCase(repository)

    @Provides
    fun provideFindWeatherByNameUseCase(
        repository: WeatherRoomRepository
    ): FindWeatherByNameUseCase = FindWeatherByNameUseCase(repository)

}

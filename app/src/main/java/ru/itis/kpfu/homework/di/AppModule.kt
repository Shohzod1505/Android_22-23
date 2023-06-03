package ru.itis.kpfu.homework.di

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import ru.itis.kpfu.homework.data.weather.datasource.local.AppDatabase
import ru.itis.kpfu.homework.data.weather.datasource.local.dao.WeatherDao
import ru.itis.kpfu.homework.utils.ResourceProvider
import ru.itis.kpfu.homework.utils.ResourceProviderImpl
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun getRoomDbInstance(
        context: Context
    ): AppDatabase {
        return AppDatabase.getAppDatabaseInstance(context)
    }

    @Singleton
    @Provides
    fun getWeatherDao(appDatabase: AppDatabase): WeatherDao {
        return appDatabase.getWeatherDao()
    }

    @Provides
    fun provideResourceProvider(
        context: Context
    ): ResourceProvider = ResourceProviderImpl(context)

    @Provides
    fun provideFusedLocationClient(
        context: Context
    ): FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

}

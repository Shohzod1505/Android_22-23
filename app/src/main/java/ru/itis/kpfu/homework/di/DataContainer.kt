package ru.itis.kpfu.homework.di

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.itis.kpfu.homework.BuildConfig
import ru.itis.kpfu.homework.data.weather.datasource.remote.WeatherApi
import ru.itis.kpfu.homework.data.weather.WeatherRepositoryImpl
import ru.itis.kpfu.homework.data.core.interceptor.ApiKeyInterceptor
import ru.itis.kpfu.homework.data.core.interceptor.MetricInterceptor
import ru.itis.kpfu.homework.data.geolocation.GeoLocationDataSource
import ru.itis.kpfu.homework.domain.weather.GetWeatherByCoordUseCase
import ru.itis.kpfu.homework.domain.weather.GetWeatherByNameUseCase
import ru.itis.kpfu.homework.utils.ResourceProvider
import ru.itis.kpfu.homework.utils.ResourceProviderImpl
import java.util.concurrent.TimeUnit

object DataContainer {

    private const val BASE_URL = BuildConfig.API_ENDPOINT

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    private val httpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ApiKeyInterceptor())
            .addInterceptor(MetricInterceptor())
            .connectTimeout(10L, TimeUnit.SECONDS)
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val weatherApi = retrofit.create(WeatherApi::class.java)

    private val weatherRepository = WeatherRepositoryImpl(weatherApi)

    val weatherByNameUseCase: GetWeatherByNameUseCase
        get() = GetWeatherByNameUseCase(weatherRepository)

    val weatherByCoordUseCase: GetWeatherByCoordUseCase
        get() = GetWeatherByCoordUseCase(weatherRepository)

    fun provideResources(
        applicationContext: Context
    ): ResourceProvider = ResourceProviderImpl(applicationContext)

    private var locationClient: FusedLocationProviderClient? = null

    fun provideGeoLocationDataSource(
        applicationContext: Context
    ) {
        locationClient = LocationServices.getFusedLocationProviderClient(applicationContext)
    }
    
    private val geoLocationDataSource: GeoLocationDataSource
        get() = GeoLocationDataSource(locationClient ?: throw RuntimeException("locationClient not provided"))

}

package ru.itis.kpfu.homework.di

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.itis.kpfu.homework.BuildConfig
import ru.itis.kpfu.homework.data.core.interceptor.ApiKeyInterceptor
import ru.itis.kpfu.homework.data.core.interceptor.MetricInterceptor
import ru.itis.kpfu.homework.data.weather.WeatherRepositoryImpl
import ru.itis.kpfu.homework.data.weather.datasource.remote.WeatherApi
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
class NetWorkModule {

    @Provides
    @Named("logging")
    fun provideLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    @Named("api")
    fun provideApiKeyInterceptor(): Interceptor = ApiKeyInterceptor()

    @Provides
    @Named("metric")
    fun provideMetricInterceptor(): Interceptor = MetricInterceptor()

    @Provides
    fun provideHttpClient(
        @Named("logging") loggingInterceptor: Interceptor,
        @Named("api") apiKeyInterceptor: Interceptor,
        @Named("metric") metricInterceptor: Interceptor,
    ): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(metricInterceptor)
            .connectTimeout(10L, TimeUnit.SECONDS)
            .build()

    @Provides
    fun provideRetrofit(
        httpClient: OkHttpClient,
        gsonFactory: GsonConverterFactory,
        @Named("base_url") baseUrl: String,
    ): Retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(gsonFactory)
            .build()

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideWeatherApi(
        retrofit: Retrofit,
    ): WeatherApi = retrofit.create(WeatherApi::class.java)

    @Provides
    @Named("base_url")
    fun provideBaseUrl(): String = BuildConfig.API_ENDPOINT

}

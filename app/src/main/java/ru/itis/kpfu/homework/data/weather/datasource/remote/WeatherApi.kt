package ru.itis.kpfu.homework.data.weather.datasource.remote

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.itis.kpfu.homework.data.weather.datasource.remote.response.WeatherResponse

interface WeatherApi {

    @GET("weather")
    fun getWeather(
        @Query("q") city: String?
    ): Single<WeatherResponse>

    @GET("weather")
    fun getWeather(
        @Query("lat") lat: Double?,
        @Query("lon") lon: Double?,
    ): Single<WeatherResponse>

}

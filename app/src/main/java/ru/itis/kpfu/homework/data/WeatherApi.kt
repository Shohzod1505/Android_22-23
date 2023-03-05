package ru.itis.kpfu.homework.data

import retrofit2.http.GET
import retrofit2.http.Query
import ru.itis.kpfu.homework.data.response.WeatherResponse

interface WeatherApi {

    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String?
    ): WeatherResponse

    @GET("weather")
    suspend fun getWeather(
        @Query("lat") lat: Double?,
        @Query("lon") lon: Double?,
    ): WeatherResponse

}

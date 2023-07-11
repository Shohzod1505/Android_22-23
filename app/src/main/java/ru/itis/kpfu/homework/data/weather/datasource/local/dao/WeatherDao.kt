package ru.itis.kpfu.homework.data.weather.datasource.local.dao

import androidx.room.*
import ru.itis.kpfu.homework.data.weather.datasource.local.entity.Weather

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(weather: Weather)

    // Some text

    @Delete
    suspend fun delete(weather: Weather)

    @Query("SELECT * FROM weather WHERE name = :name")
    suspend fun findByName(name: String): Weather?

}

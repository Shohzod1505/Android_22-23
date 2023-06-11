package ru.itis.kpfu.homework.data.weather.datasource.local.dao

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.itis.kpfu.homework.data.weather.datasource.local.entity.Weather

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(weather: Weather): Completable

    @Delete
    fun delete(weather: Weather): Completable

    @Query("SELECT * FROM weather WHERE name = :name")
    fun findByName(name: String): Single<Weather>

}

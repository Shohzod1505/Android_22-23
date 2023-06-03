package ru.itis.kpfu.homework.data.weather.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.itis.kpfu.homework.data.weather.datasource.local.dao.WeatherDao
import ru.itis.kpfu.homework.data.weather.datasource.local.entity.Weather

@Database(entities = [Weather::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getWeatherDao(): WeatherDao

    companion object {
        private const val DATABASE_NAME = "weather.db"
        private var db_instance: AppDatabase? = null

        fun getAppDatabaseInstance(context: Context): AppDatabase {

            if (db_instance == null) {
                db_instance = Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java, DATABASE_NAME
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return db_instance as AppDatabase
        }
    }

}

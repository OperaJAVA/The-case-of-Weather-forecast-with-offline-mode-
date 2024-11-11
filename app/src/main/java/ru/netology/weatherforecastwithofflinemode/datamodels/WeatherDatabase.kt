package ru.netology.weatherforecastwithofflinemode.datamodels

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import ru.netology.weatherforecastwithofflinemode.dao.CityDao

@Database(entities = [City::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao

    companion object {
        @Volatile private var instance: WeatherDatabase? = null

        fun getDatabase(context: Context): WeatherDatabase {
            return instance ?: synchronized(this) {
                val tempInstance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDatabase::class.java,
                    "weather_database"
                ).build()
                instance = tempInstance
                tempInstance
            }
        }
    }
}
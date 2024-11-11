@file:Suppress("EXTENSION_SHADOWED_BY_MEMBER")

package ru.netology.weatherforecastwithofflinemode.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ru.netology.weatherforecastwithofflinemode.datamodels.City
import kotlin.coroutines.Continuation

@Dao
interface CityDao {
    @Insert
    suspend fun insert(city: City)

    @Update
    suspend fun update(city: City)

    @Query("SELECT * FROM cities")
    suspend fun getAllCities(): List<City>

    @Query("DELETE FROM cities WHERE id = :cityId")
    suspend fun delete(cityId: Int)
}


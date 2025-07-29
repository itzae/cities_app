package com.itgonca.citiesapp.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.itgonca.citiesapp.data.local.db.entity.CityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.NONE)
    suspend fun insertAllCities(cities: List<CityEntity>)

    @Query("SELECT * FROM cities ORDER BY name ASC")
    suspend fun getAllCities(): List<CityEntity>

    @Query("SELECT COUNT(rowId) FROM cities")
    suspend fun getCitiesSize(): Int

    @Query("SELECT * FROM cities WHERE name LIKE :query ")
    fun searchCitiesByPrefix(query: String): Flow<List<CityEntity>>
}
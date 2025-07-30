package com.itgonca.citiesapp.data.local.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.itgonca.citiesapp.data.local.db.entity.CityEntity

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.NONE)
    suspend fun insertAllCities(cities: List<CityEntity>)

    @Query("SELECT * FROM cities ORDER BY name ASC")
    fun getAllCities(): PagingSource<Int, CityEntity>

    @Query("SELECT COUNT(name) FROM cities")
    suspend fun getCitiesSize(): Int

    @Query("SELECT * FROM cities WHERE name LIKE :query ")
    fun searchCitiesByPrefix(query: String): PagingSource<Int, CityEntity>

    @Query("UPDATE cities SET isFavorite = :isFavorite WHERE rowId = :id ")
    suspend fun updateFavoriteCity(id: Int, isFavorite: Boolean)

    @Query("SELECT * FROM cities WHERE isFavorite = 1")
    fun getFavoritesCities(): PagingSource<Int, CityEntity>

    @Query("SELECT * FROM cities WHERE name LIKE :query AND isFavorite = 1")
    fun searchFavoritesCities(query: String): PagingSource<Int, CityEntity>
}
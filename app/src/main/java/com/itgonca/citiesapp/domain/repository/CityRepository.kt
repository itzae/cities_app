package com.itgonca.citiesapp.domain.repository

import androidx.paging.PagingData
import com.itgonca.citiesapp.domain.model.City
import com.itgonca.citiesapp.domain.model.CityWeather
import kotlinx.coroutines.flow.Flow

interface CityRepository {
    fun getCities(): Flow<PagingData<City>>
    fun searchCities(query: String): Flow<PagingData<City>>
    suspend fun updateFavoriteCity(id: Int, isFavorite: Boolean)
    fun getFavoritesCities(): Flow<PagingData<City>>
    fun searchFavorites(query: String): Flow<PagingData<City>>
    suspend fun getCityWeather(latitude: Double, longitude: Double): CityWeather
}
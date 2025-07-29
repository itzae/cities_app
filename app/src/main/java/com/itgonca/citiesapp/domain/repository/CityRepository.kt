package com.itgonca.citiesapp.domain.repository

import com.itgonca.citiesapp.domain.model.City
import kotlinx.coroutines.flow.Flow

interface CityRepository {
    fun getCities(): Flow<List<City>>
    fun searchCities(query: String): Flow<List<City>>
    suspend fun updateFavoriteCity(id: Int, isFavorite: Boolean)
}
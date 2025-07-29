package com.itgonca.citiesapp.domain.repository

import androidx.paging.PagingData
import com.itgonca.citiesapp.domain.model.City
import kotlinx.coroutines.flow.Flow

interface CityRepository {
    fun getCities(): Flow<PagingData<City>>
    fun searchCities(query: String): Flow<PagingData<City>>
    suspend fun updateFavoriteCity(id: Int, isFavorite: Boolean)
}
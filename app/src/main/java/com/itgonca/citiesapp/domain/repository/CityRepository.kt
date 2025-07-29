package com.itgonca.citiesapp.domain.repository

import com.itgonca.citiesapp.domain.model.City
import kotlinx.coroutines.flow.Flow

interface CityRepository {
    suspend fun getCities(): List<City>
    fun searchCities(query: String): Flow<List<City>>
}
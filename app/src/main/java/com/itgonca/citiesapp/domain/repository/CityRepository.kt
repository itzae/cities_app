package com.itgonca.citiesapp.domain.repository

import com.itgonca.citiesapp.domain.model.City

interface CityRepository {
    suspend fun getCities(): List<City>
}
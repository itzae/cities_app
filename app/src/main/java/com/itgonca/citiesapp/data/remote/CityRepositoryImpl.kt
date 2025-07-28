package com.itgonca.citiesapp.data.remote

import com.itgonca.citiesapp.domain.model.City
import com.itgonca.citiesapp.domain.repository.CityRepository
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(private val cityApi: CityApi) : CityRepository {
    override suspend fun getCities(): List<City> =
        cityApi.getCities().asSequence()
            .map {
                City(
                    id = it.id,
                    name = it.name,
                    country = it.country,
                    latitude = it.location.lat,
                    longitude = it.location.lon
                )
            }
            .sortedBy { it.name }
            .toList()

}
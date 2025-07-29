package com.itgonca.citiesapp.data.remote

import com.itgonca.citiesapp.data.local.db.dao.CityDao
import com.itgonca.citiesapp.data.local.db.entity.toDomain
import com.itgonca.citiesapp.data.local.db.entity.toEntity
import com.itgonca.citiesapp.domain.model.City
import com.itgonca.citiesapp.domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val cityApi: CityApi,
    private val cityDao: CityDao
) : CityRepository {
    override suspend fun getCities(): List<City> {
        val result = if (cityDao.getCitiesSize() == 0) {
            val response = cityApi.getCities().asSequence()
                .map {
                    City(
                        id = it.id,
                        name = it.name,
                        country = it.country,
                        latitude = it.location.lat,
                        longitude = it.location.lon
                    )
                }
                .toList()
            cityDao.insertAllCities(response.map { it.toEntity() })
            cityDao.getAllCities().map { it.toDomain() }
        } else {
            cityDao.getAllCities().map { it.toDomain() }
        }
        return result
    }

    override fun searchCities(query: String): Flow<List<City>> {
        return cityDao.searchCitiesByPrefix("$query%")
            .map {
                it.map { entity -> entity.toDomain() }
            }
    }
}
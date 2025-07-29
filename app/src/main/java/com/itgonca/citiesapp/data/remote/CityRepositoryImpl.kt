package com.itgonca.citiesapp.data.remote

import com.itgonca.citiesapp.data.local.db.dao.CityDao
import com.itgonca.citiesapp.data.local.db.entity.toDomain
import com.itgonca.citiesapp.data.local.db.entity.toEntity
import com.itgonca.citiesapp.domain.model.City
import com.itgonca.citiesapp.domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val cityApi: CityApi,
    private val cityDao: CityDao
) : CityRepository {
    override fun getCities(): Flow<List<City>> = flow {
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
            cityDao.getAllCities().map { citiesEntity ->
                citiesEntity.asSequence().map { it.toDomain() }.sortedBy { it.name.lowercase() }
                    .toList()
            }

        } else {
            cityDao.getAllCities().map { citiesEntity ->
                citiesEntity.asSequence().map { it.toDomain() }.sortedBy { it.name.lowercase() }
                    .toList()
            }
        }
        emitAll(result)
    }

    override fun searchCities(query: String): Flow<List<City>> {
        return cityDao.searchCitiesByPrefix("$query%")
            .map {
                it.map { entity -> entity.toDomain() }
            }
    }

    override suspend fun updateFavoriteCity(id: Int, isFavorite: Boolean) {
        cityDao.updateFavoriteCity(id, isFavorite)
    }
}
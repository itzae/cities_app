package com.itgonca.citiesapp.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
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
    /**
     * This method obtains all the cities, performs a check to obtain the cities remotely and insert
     * them into the database, if there are records the cities are obtained locally
     * @return a [Flow] with the page [City] object.
     */
    override fun getCities(): Flow<PagingData<City>> = flow {
        if (cityDao.getCitiesSize() == 0) {
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
            emitAll(
                Pager(
                    config = PagingConfig(pageSize = 50, enablePlaceholders = false),
                    pagingSourceFactory = { cityDao.getAllCities() })
                    .flow
                    .map { pagingData ->
                        pagingData.map { city -> city.toDomain() }
                    })
        } else {
            emitAll(
                Pager(
                    config = PagingConfig(pageSize = 50, enablePlaceholders = false),
                    pagingSourceFactory = { cityDao.getAllCities() })
                    .flow
                    .map { pagingData ->
                        pagingData.map { city -> city.toDomain() }
                    })
        }
    }

    /**
     * This method perform an indexed search to get all the results that match the query provided
     * @param query is the search text
     * @return a [Flow] with the page [City] object.
     */
    override fun searchCities(query: String): Flow<PagingData<City>> = flow {
        emitAll(
            Pager(
                config = PagingConfig(pageSize = 50, enablePlaceholders = false),
                pagingSourceFactory = { cityDao.searchCitiesByPrefix("$query%") })
                .flow
                .map { pagingData ->
                    pagingData.map { city -> city.toDomain() }
                })
    }

    /**
     * This method updates the selected city to mark it as a favorite or not.
     * @param id is the city id
     * @param isFavorite is the state of the selected city
     */
    override suspend fun updateFavoriteCity(id: Int, isFavorite: Boolean) {
        cityDao.updateFavoriteCity(id, isFavorite)
    }
}
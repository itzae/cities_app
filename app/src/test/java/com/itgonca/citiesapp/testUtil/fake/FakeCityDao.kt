package com.itgonca.citiesapp.testUtil.fake

import androidx.paging.PagingSource
import com.itgonca.citiesapp.data.local.db.dao.CityDao
import com.itgonca.citiesapp.data.local.db.entity.CityEntity

class FakeCityDao : CityDao {
    private var citiesDao = mutableListOf<CityEntity>()
    override suspend fun insertAllCities(cities: List<CityEntity>) {
        citiesDao.addAll(cities)
    }

    override fun getAllCities(): PagingSource<Int, CityEntity> = FakePagingSource(citiesDao)


    override suspend fun getCitiesSize(): Int = citiesDao.size

    override fun searchCitiesByPrefix(query: String): PagingSource<Int, CityEntity> {
        val citiesFiltered =
            citiesDao.filter { it.name.startsWith(query.replace("%", ""), ignoreCase = true) }
        return FakePagingSource(citiesFiltered)
    }

    override suspend fun updateFavoriteCity(id: Int, isFavorite: Boolean) {
        citiesDao =
            citiesDao.map {
                if (it.id == id) it.copy(isFavorite = isFavorite) else it
            }.toMutableList()
    }

    override fun getFavoritesCities(): PagingSource<Int, CityEntity> {
        val citiesFiltered =
            citiesDao.filter { it.isFavorite }
        return FakePagingSource(citiesFiltered)
    }

    override fun searchFavoritesCities(query: String): PagingSource<Int, CityEntity> {
        val citiesFiltered =
            citiesDao.filter {
                it.name.startsWith(
                    query.replace("%", ""),
                    ignoreCase = true
                ) && it.isFavorite
            }
        return FakePagingSource(citiesFiltered)
    }
}
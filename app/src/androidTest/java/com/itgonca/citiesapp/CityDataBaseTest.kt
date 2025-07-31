package com.itgonca.citiesapp

import android.content.Context
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.testing.TestPager
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.itgonca.citiesapp.data.local.db.CityDataBase
import com.itgonca.citiesapp.data.local.db.dao.CityDao
import com.itgonca.citiesapp.testUtil.citiesEntity
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class CityDataBaseTest {
    private lateinit var cityDao: CityDao
    private lateinit var db: CityDataBase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, CityDataBase::class.java).build()
        cityDao = db.cityDao()
    }

    @Test
    @Throws(IOException::class)
    fun insertCitiesIntoDbAndFetchAllCities() = runTest {
        cityDao.insertAllCities(citiesEntity)
        val citiesSource = TestPager(
            config = PagingConfig(pageSize = 10),
            pagingSource = cityDao.getAllCities()
        )

        val result = citiesSource.refresh() as PagingSource.LoadResult.Page
        assertThat(result).containsExactlyElementsIn(citiesEntity)
    }

    @Test
    @Throws(IOException::class)
    fun searchCityByNameWithPrefix() = runTest {
        val prefixQuery = "alb"
        //Insert cities
        // Alabama, US
        // Albuquerque, US
        // Anaheim, US
        // Arizona, US
        // Sidney, AU
        cityDao.insertAllCities(citiesEntity)

        val citiesSource = TestPager(
            config = PagingConfig(pageSize = 10),
            pagingSource = cityDao.searchCitiesByPrefix("$prefixQuery%")
        )
        //This filter the list to evaluate against the database result.
        val citiesFiltered = citiesEntity.filter { it.name.startsWith(prefixQuery, ignoreCase = true) }

        val result = citiesSource.refresh() as PagingSource.LoadResult.Page

        assertThat(result).containsExactlyElementsIn(citiesFiltered)
    }

    @Test
    @Throws(IOException::class)
    fun getAllFavoriteCities() = runTest {
        cityDao.insertAllCities(citiesEntity)
        val citiesSource = TestPager(
            config = PagingConfig(pageSize = 10),
            pagingSource = cityDao.getFavoritesCities()
        )

        val result = citiesSource.refresh() as PagingSource.LoadResult.Page
        assertThat(result).containsExactlyElementsIn(citiesEntity.filter { it.isFavorite })
    }

    @Test
    @Throws(IOException::class)
    fun searchFavoriteCityByNameWithPrefix() = runTest {
        val prefixQuery = "alb"
        //Insert cities
        // Alabama, US
        // Albuquerque, US
        // Anaheim, US
        // Arizona, US
        // Sidney, AU
        cityDao.insertAllCities(citiesEntity)

        val citiesSource = TestPager(
            config = PagingConfig(pageSize = 10),
            pagingSource = cityDao.searchFavoritesCities("$prefixQuery%")
        )
        //This filter the list to evaluate against the database result.
        val citiesFiltered = citiesEntity.filter { it.name.startsWith(prefixQuery, ignoreCase = true) && it.isFavorite }

        val result = citiesSource.refresh() as PagingSource.LoadResult.Page

        assertThat(result).containsExactlyElementsIn(citiesFiltered)
    }

    @After
    fun closeDb() {
        db.close()
    }

}
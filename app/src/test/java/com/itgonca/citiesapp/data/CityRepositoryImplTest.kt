package com.itgonca.citiesapp.data

import androidx.paging.testing.asSnapshot
import com.google.common.truth.Truth.assertThat
import com.itgonca.citiesapp.data.local.db.dao.CityDao
import com.itgonca.citiesapp.data.remote.CityApi
import com.itgonca.citiesapp.data.remote.WeatherApi
import com.itgonca.citiesapp.domain.repository.CityRepository
import com.itgonca.citiesapp.testUtil.cities
import com.itgonca.citiesapp.testUtil.citiesEntity
import com.itgonca.citiesapp.testUtil.fake.FakeCityApi
import com.itgonca.citiesapp.testUtil.fake.FakeCityDao
import com.itgonca.citiesapp.testUtil.fake.FakeWeatherApi
import com.itgonca.citiesapp.testUtil.weatherResponse
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.io.IOException

class CityRepositoryImplTest {
    private lateinit var cityApi: CityApi
    private lateinit var weatherApi: WeatherApi
    private lateinit var cityDao: CityDao
    private lateinit var cityRepository: CityRepository

    @Before
    fun setup() {
        cityApi = FakeCityApi()
        cityDao = FakeCityDao()
        weatherApi = FakeWeatherApi()
        cityRepository =
            CityRepositoryImpl(cityApi = cityApi, cityDao = cityDao, weatherApi = weatherApi)
    }

    @Test
    @Throws(IOException::class)
    fun `Get all cities from remote and then insert in db`() = runTest {
        val citiesResult = cityRepository.getCities()
        val result = citiesResult.asSnapshot()
        assertThat(result).containsExactlyElementsIn(cities)
    }

    @Test
    fun `Search a city by name with prefix`() = runTest {
        cityDao.insertAllCities(citiesEntity)
        val citiesResult = cityRepository.searchCities("alb")
        val result = citiesResult.asSnapshot()
        assertThat(result)
            .containsExactlyElementsIn(
                cities.filter { it.name.startsWith("alb", ignoreCase = true) }
            )
    }

    @Test
    fun `Update selected city to favorite`() = runTest {
        cityDao.insertAllCities(citiesEntity)
        cityRepository.updateFavoriteCity(102, true)
        val citiesResult = cityRepository.getFavoritesCities().asSnapshot()
        assertThat(citiesResult).contains(cities[1].copy(isFavorite = true))
    }

    @Test
    fun getFavoritesCities() = runTest {
        cityDao.insertAllCities(citiesEntity.map { it.copy(isFavorite = true) })
        val citiesResult = cityRepository.getFavoritesCities().asSnapshot()
        assertThat(citiesResult).containsExactlyElementsIn(cities.map { it.copy(isFavorite = true) })
    }

    @Test
    fun searchFavorites() = runTest {
        cityDao.insertAllCities(citiesEntity.map { it.copy(isFavorite = true) })
        val citiesResult = cityRepository.searchFavorites("alb").asSnapshot()
        assertThat(citiesResult).contains(cities.map { it.copy(isFavorite = true) }[1])
    }

    @Test
    fun `Get the city weather`() = runTest {
        val result = cityRepository.getCityWeather(latitude = 19.4285, longitude = -99.1277)
        assertEquals(weatherResponse.name, result.cityName)
        assertEquals(weatherResponse.main.humidity, result.weather.humidity)
    }

}
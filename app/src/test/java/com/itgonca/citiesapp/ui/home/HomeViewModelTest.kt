package com.itgonca.citiesapp.ui.home

import androidx.paging.testing.asSnapshot
import com.itgonca.citiesapp.data.CityRepositoryImpl
import com.itgonca.citiesapp.data.local.db.dao.CityDao
import com.itgonca.citiesapp.data.remote.CityApi
import com.itgonca.citiesapp.data.remote.WeatherApi
import com.itgonca.citiesapp.domain.repository.CityRepository
import com.itgonca.citiesapp.testUtil.cities
import com.itgonca.citiesapp.testUtil.fake.FakeCityApi
import com.itgonca.citiesapp.testUtil.fake.FakeCityDao
import com.itgonca.citiesapp.testUtil.fake.FakeWeatherApi
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class HomeViewModelTest {
    private lateinit var cityApi: CityApi
    private lateinit var weatherApi: WeatherApi

    private lateinit var cityDao: CityDao
    private lateinit var cityRepository: CityRepository
    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        cityApi = FakeCityApi()
        cityDao = FakeCityDao()
        weatherApi = FakeWeatherApi()
        cityRepository =
            CityRepositoryImpl(cityApi = cityApi, cityDao = cityDao, weatherApi = weatherApi)
        homeViewModel = HomeViewModel(cityRepository)
    }

    @Test
    fun `Get all cities when cities stateflow is subscribed`() = runTest {
        val items = homeViewModel.cities.asSnapshot()
        assertEquals(cities, items)
    }

    @Test
    fun `Search result should return Alabama`() = runTest {
        //Insert the lis of the cities to be retrieved later
        cityRepository.getCities().asSnapshot()
        //Perform the city search
        homeViewModel.onSearch("ala")
        //Collects the changes in the stateflow which returns a PagingData
        val items = homeViewModel.cities.asSnapshot()

        assertEquals(cities[0], items[0])
    }

    @Test
    fun `Search result should return cities that begin with A`() = runTest {
        //Insert the lis of the cities to be retrieved later
        cityRepository.getCities().asSnapshot()
        //Perform the city search
        homeViewModel.onSearch("a")
        //Collects the changes in the stateflow which returns a PagingData
        val items = homeViewModel.cities.asSnapshot()

        assertEquals(3, items.size)
    }

    @Test
    fun `Search result should return cities that begin with S`() = runTest {
        //Insert the lis of the cities to be retrieved later
        cityRepository.getCities().asSnapshot()
        //Perform the city search
        homeViewModel.onSearch("s")
        //Collects the changes in the stateflow which returns a PagingData
        val items = homeViewModel.cities.asSnapshot()

        assertEquals(1, items.size)
    }

    @Test
    fun `Search result should return all the cities`() = runTest {
        //Insert the lis of the cities to be retrieved later
        cityRepository.getCities().asSnapshot()
        //Perform the city search
        homeViewModel.onSearch("")
        //Collects the changes in the stateflow which returns a PagingData
        val items = homeViewModel.cities.asSnapshot()

        assertEquals(4, items.size)
    }

    @Test
    fun `Get Alabama city as a favorite city in the search`() = runTest {
        //Insert the lis of the cities to be retrieved later
        cityRepository.getCities().asSnapshot()
        homeViewModel.onSelectFavorite(cities[0].id,true)
        //Perform the city search
        homeViewModel.onSearch("alab")
        //Collects the changes in the stateflow which returns a PagingData
        val items = homeViewModel.favoriteCities.asSnapshot()

        assertEquals(1, items.size)
    }

}
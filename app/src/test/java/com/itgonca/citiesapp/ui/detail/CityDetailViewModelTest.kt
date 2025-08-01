package com.itgonca.citiesapp.ui.detail

import com.itgonca.citiesapp.data.CityRepositoryImpl
import com.itgonca.citiesapp.data.local.db.dao.CityDao
import com.itgonca.citiesapp.data.remote.CityApi
import com.itgonca.citiesapp.data.remote.WeatherApi
import com.itgonca.citiesapp.domain.repository.CityRepository
import com.itgonca.citiesapp.testUtil.fake.FakeCityApi
import com.itgonca.citiesapp.testUtil.fake.FakeCityDao
import com.itgonca.citiesapp.testUtil.fake.FakeWeatherApi
import com.itgonca.citiesapp.testUtil.weatherResponse
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class CityDetailViewModelTest {
    private lateinit var cityApi: CityApi
    private lateinit var weatherApi: WeatherApi

    private lateinit var cityDao: CityDao
    private lateinit var cityRepository: CityRepository
    private lateinit var cityDetailViewModel: CityDetailViewModel

    @Before
    fun setup() {
        cityApi = FakeCityApi()
        cityDao = FakeCityDao()
        weatherApi = FakeWeatherApi()
        cityRepository = CityRepositoryImpl(
            cityApi = cityApi,
            weatherApi = weatherApi,
            cityDao = cityDao
        )
        cityDetailViewModel = CityDetailViewModel(cityRepository)
    }

    @Test
    fun `Get the weather of the selected city`() = runTest {
        cityDetailViewModel.fetchCityWeather(latitude = 19.4285, longitude = -99.1277)
        advanceUntilIdle()
        assertEquals(weatherResponse.name, cityDetailViewModel.weather.value.weather.cityName)

    }
}
package com.itgonca.citiesapp.testUtil.fake

import com.itgonca.citiesapp.data.remote.WeatherApi
import com.itgonca.citiesapp.data.remote.WeatherResponse
import com.itgonca.citiesapp.testUtil.weatherResponse

class FakeWeatherApi : WeatherApi {

    override suspend fun getWeatherByLocation(
        long: Double,
        lat: Double,
        units: String,
        lang: String,
        mode: String
    ): WeatherResponse = weatherResponse
}
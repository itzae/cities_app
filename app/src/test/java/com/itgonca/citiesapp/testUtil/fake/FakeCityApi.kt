package com.itgonca.citiesapp.testUtil.fake

import com.itgonca.citiesapp.data.remote.CityApi
import com.itgonca.citiesapp.data.remote.CityResponse
import com.itgonca.citiesapp.testUtil.citiesResponse

class FakeCityApi : CityApi {
    override suspend fun getCities(): List<CityResponse> = citiesResponse
}
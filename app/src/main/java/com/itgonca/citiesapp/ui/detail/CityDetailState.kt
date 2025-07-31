package com.itgonca.citiesapp.ui.detail

import com.itgonca.citiesapp.domain.model.CityWeather

data class CityDetailState(
    val isLoading: Boolean = false,
    val weather: CityWeather = CityWeather(),
    val isError: Boolean = false
)
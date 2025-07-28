package com.itgonca.citiesapp.ui.home

import com.itgonca.citiesapp.domain.model.City

data class HomeState(val isLoading: Boolean = true, val cities: List<City> = emptyList())

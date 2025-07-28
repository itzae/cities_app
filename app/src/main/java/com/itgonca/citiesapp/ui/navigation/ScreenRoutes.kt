package com.itgonca.citiesapp.ui.navigation

import kotlinx.serialization.Serializable

sealed interface ScreenRoutes {
    @Serializable
    data object HomeScreen : ScreenRoutes

    @Serializable
    data class CityMapScreen(
        val id: Int,
        val name: String,
        val latitude: Double,
        val longitude: Double
    ) : ScreenRoutes
}
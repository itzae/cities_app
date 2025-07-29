package com.itgonca.citiesapp.domain.model

data class City(
    val id: Int = 0,
    val name: String = "",
    val country: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val isFavorite:Boolean = false,
)
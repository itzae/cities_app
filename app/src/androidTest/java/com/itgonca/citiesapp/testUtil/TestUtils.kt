package com.itgonca.citiesapp.testUtil

import com.itgonca.citiesapp.data.local.db.entity.CityEntity
import com.itgonca.citiesapp.domain.model.City

val citiesEntity = listOf(
    CityEntity(
        id = 101,
        name = "Alabama",
        country = "US",
        latitude = "0.0",
        longitude = "0.0",
        isFavorite = false
    ),
    CityEntity(
        id = 102,
        name = "Albuquerque",
        country = "US",
        latitude = "0.0",
        longitude = "0.0",
        isFavorite = true
    ),
    CityEntity(
        id = 103,
        name = "Anaheim",
        country = "US",
        latitude = "0.0",
        longitude = "0.0",
        isFavorite = false
    ),
    CityEntity(
        id = 104,
        name = "Sydney",
        country = "AU",
        latitude = "0.0",
        longitude = "0.0",
        isFavorite = true
    )
)

val cities = listOf(
    City(
        id = 101,
        name = "Alabama",
        country = "US",
        latitude = 0.0,
        longitude = 0.0,
        isFavorite = false
    ),
    City(
        id = 102,
        name = "Albuquerque",
        country = "US",
        latitude = 0.0,
        longitude = 0.0,
        isFavorite = true
    ),
    City(
        id = 103,
        name = "Anaheim",
        country = "US",
        latitude = 0.0,
        longitude = 0.0,
        isFavorite = false
    ),
    City(
        id = 104,
        name = "Sydney",
        country = "AU",
        latitude = 0.0,
        longitude = 0.0,
        isFavorite = true
    )
)

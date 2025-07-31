package com.itgonca.citiesapp.testUtil

import com.itgonca.citiesapp.data.local.db.entity.CityEntity
import com.itgonca.citiesapp.data.remote.CityResponse
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
        isFavorite = false
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
        isFavorite = false
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
        isFavorite = false
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
        isFavorite = false
    )
)

val citiesResponse = listOf(
    CityResponse(
        id = 101,
        name = "Alabama",
        country = "US",
        location = CityResponse.Location(
            lat = 0.0,
            lon = 0.0
        )
    ),
    CityResponse(
        id = 102,
        name = "Albuquerque",
        country = "US",
        location = CityResponse.Location(
            lat = 0.0,
            lon = 0.0
        )
    ),
    CityResponse(
        id = 103,
        name = "Anaheim",
        country = "US",
        location = CityResponse.Location(
            lat = 0.0,
            lon = 0.0
        )
    )
    ,CityResponse(
        id = 104,
        name = "Sydney",
        country = "AU",
        location = CityResponse.Location(
            lat = 0.0,
            lon = 0.0
        )
    )
)

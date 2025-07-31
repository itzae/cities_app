package com.itgonca.citiesapp.testUtil

import com.itgonca.citiesapp.data.local.db.entity.CityEntity
import com.itgonca.citiesapp.domain.model.City
import com.itgonca.citiesapp.domain.model.CityWeather

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

val cityWeatherInformation = CityWeather(
    cityName = "Ciudad de México",
    country = "MX",
    weather = CityWeather.Weather(
        description = "Nuboso",
        iconWeather = "https://openweathermap.org/img/wn/04n@2x.png",
        temperature = 17.0,
        temperatureFeelLike = 12.0,
        humidity = 24.0,
        tempMin = 25.33,
        tempMax = 25.44,
        wind = 1.73,
        windDirection = "65",
        visibility = 1000.0,
        clouds = 82.0
    ),
)

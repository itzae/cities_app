package com.itgonca.citiesapp.testUtil

import com.itgonca.citiesapp.data.local.db.entity.CityEntity
import com.itgonca.citiesapp.data.remote.CityResponse
import com.itgonca.citiesapp.data.remote.WeatherResponse
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
    ), CityResponse(
        id = 104,
        name = "Sydney",
        country = "AU",
        location = CityResponse.Location(
            lat = 0.0,
            lon = 0.0
        )
    )
)

val weatherResponse = WeatherResponse(
    base = "stations",
    clouds = WeatherResponse.Clouds(
        cloudiness = 100.0,
        unit = "%"
    ),
    cod = 200.0,
    coord = WeatherResponse.Coord(
        lat = 19.4285,
        lon = -99.1277
    ),
    dt = 1754006886.0,
    dtTxt = "2025-08-01T00:08:06.000Z",
    id = 3530597.0,
    main = WeatherResponse.Main(
        groundLevelPressure = 765.0,
        humidity = 67.0,
        humidityUnit = "%",
        pressure = 1017.0,
        pressureUnit = "hpa",
        seaLevelPressure = 1017.0,
        temperature = 17.75,
        temperatureFeelsLike = 17.33,
        temperatureMax = 17.96,
        temperatureMin = 17.75,
        temperatureUnit = "°C"
    ),
    name = "Ciudad de Mexico",
    rain = WeatherResponse.Rain(
        amount = 0.9,
        unit = "mm in the last 1 hour"
    ),
    snow = WeatherResponse.Snow(
        amount = 0.0,
        unit = "mm in the last 1 hour"
    ),
    summery = "light rain, 17.75°C, feels like 17.33°C, winds 5.14 meter/sec from North, 67% humidity",
    sys = WeatherResponse.Sys(
        country = "MX",
        id = 47729.0,
        sunrise = 1753963950.0,
        sunriseTxt = "2025-07-31T12:12:30.000Z",
        sunset = 1754010786.0,
        sunsetTxt = "2025-08-01T01:13:06.000Z",
        type = 2.0
    ),
    timezone = -21600.0,
    visibilityDistance = 10000.0,
    visibilityUnit = "m",
    weather = listOf(
        WeatherResponse.Weather(
            description = "Lluvia ligera",
            icon = "https://openweathermap.org/img/wn/10d@2x.png",
            id = 500.0,
            main = "Rain"
        )
    ),
    wind = WeatherResponse.Wind(
        degrees = 360.0,
        direction = "North",
        gustSpeed = null,
        speed = 5.14,
        speedUnit = "meter/sec"
    ),
)

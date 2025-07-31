package com.itgonca.citiesapp.domain.model

data class CityWeather(
    val cityName: String = "",
    val country: String = "",
    val weather: Weather = Weather()
) {
    data class Weather(
        val description: String = "",
        val iconWeather: String = "",
        val temperature: Double = 0.0,
        val temperatureFeelLike: Double = 0.0,
        val humidity: Double = 0.0,
        val tempMin: Double = 0.0,
        val tempMax: Double = 0.0,
        val wind: Double = 0.0,
        val windDirection: String ="",
        val visibility: Double = 0.0,
        val clouds: Double = 0.0
    )
}
package com.itgonca.citiesapp.data.remote


import com.google.gson.annotations.SerializedName
import com.itgonca.citiesapp.domain.model.CityWeather

data class WeatherResponse(
    @SerializedName("base")
    val base: String,
    @SerializedName("clouds")
    val clouds: Clouds,
    @SerializedName("cod")
    val cod: Double,
    @SerializedName("coord")
    val coord: Coord,
    @SerializedName("dt")
    val dt: Double,
    @SerializedName("dt_txt")
    val dtTxt: String,
    @SerializedName("id")
    val id: Double,
    @SerializedName("main")
    val main: Main,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("rain")
    val rain: Rain,
    @SerializedName("snow")
    val snow: Snow,
    @SerializedName("summery")
    val summery: String,
    @SerializedName("sys")
    val sys: Sys,
    @SerializedName("timezone")
    val timezone: Double,
    @SerializedName("visibility_distance")
    val visibilityDistance: Double,
    @SerializedName("visibility_unit")
    val visibilityUnit: String,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("wind")
    val wind: Wind
) {
    data class Clouds(
        @SerializedName("cloudiness")
        val cloudiness: Double,
        @SerializedName("unit")
        val unit: String
    )

    data class Coord(
        @SerializedName("lat")
        val lat: Double,
        @SerializedName("lon")
        val lon: Double
    )

    data class Main(
        @SerializedName("ground_level_pressure")
        val groundLevelPressure: Double,
        @SerializedName("humidity")
        val humidity: Double,
        @SerializedName("humidity_unit")
        val humidityUnit: String,
        @SerializedName("pressure")
        val pressure: Double,
        @SerializedName("pressure_unit")
        val pressureUnit: String,
        @SerializedName("sea_level_pressure")
        val seaLevelPressure: Double,
        @SerializedName("temprature")
        val temprature: Double,
        @SerializedName("temprature_feels_like")
        val tempratureFeelsLike: Double,
        @SerializedName("temprature_max")
        val tempratureMax: Double,
        @SerializedName("temprature_min")
        val tempratureMin: Double,
        @SerializedName("temprature_unit")
        val tempratureUnit: String
    )

    data class Rain(
        @SerializedName("amount")
        val amount: Double,
        @SerializedName("unit")
        val unit: String
    )

    data class Snow(
        @SerializedName("amount")
        val amount: Double,
        @SerializedName("unit")
        val unit: String
    )

    data class Sys(
        @SerializedName("country")
        val country: String? = null,
        @SerializedName("id")
        val id: Double,
        @SerializedName("sunrise")
        val sunrise: Double,
        @SerializedName("sunrise_txt")
        val sunriseTxt: String,
        @SerializedName("sunset")
        val sunset: Double,
        @SerializedName("sunset_txt")
        val sunsetTxt: String,
        @SerializedName("type")
        val type: Double
    )

    data class Weather(
        @SerializedName("description")
        val description: String,
        @SerializedName("icon")
        val icon: String,
        @SerializedName("id")
        val id: Double,
        @SerializedName("main")
        val main: String
    )

    data class Wind(
        @SerializedName("degrees")
        val degrees: Double,
        @SerializedName("direction")
        val direction: String,
        @SerializedName("gust_speed")
        val gustSpeed: Double,
        @SerializedName("speed")
        val speed: Double,
        @SerializedName("speed_unit")
        val speedUnit: String
    )
}

fun WeatherResponse.toDomain() = with(this) {
    CityWeather(
        cityName = name ?: "",
        country = sys.country ?: "",
        weather = CityWeather.Weather(
            description = weather.first().description,
            iconWeather = weather.first().icon,
            temperature = main.temprature,
            temperatureFeelLike = main.tempratureFeelsLike,
            humidity = main.temprature,
            tempMin = main.tempratureMin,
            tempMax = main.tempratureMax,
            wind = wind.speed,
            windDirection = wind.direction,
            visibility = visibilityDistance,
            clouds = clouds.cloudiness
        )
    )
}
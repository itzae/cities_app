package com.itgonca.citiesapp.data.remote

import com.itgonca.citiesapp.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather/current")
    @Headers(
        "x-rapidapi-host: weather-api167.p.rapidapi.com",
        "x-rapidapi-key: ${BuildConfig.API_KEY}"
    )
    suspend fun getWeatherByLocation(
        @Query("lon") long: Double,
        @Query("lat") lat: Double,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "es",
        @Query("mode") mode: String = "json",
    ): WeatherResponse
}
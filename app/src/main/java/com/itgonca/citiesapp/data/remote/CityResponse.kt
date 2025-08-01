package com.itgonca.citiesapp.data.remote

import com.google.gson.annotations.SerializedName

data class CityResponse(
    @SerializedName("coord")
    val location: Location,
    @SerializedName("country")
    val country: String,
    @SerializedName("_id")
    val id: Int,
    @SerializedName("name")
    val name: String
) {
    data class Location(
        @SerializedName("lat")
        val lat: Double,
        @SerializedName("lon")
        val lon: Double
    )

}
package com.itgonca.citiesapp.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.itgonca.citiesapp.domain.model.City

@Entity(tableName = "cities", indices = [Index(value = ["name"])])
data class CityEntity(
    @PrimaryKey @ColumnInfo("rowId") val id: Int = 0,
    val name: String,
    val country: String,
    val latitude: String,
    val longitude: String,
    val isFavorite: Boolean = false
)

fun City.toEntity() = with(this) {
    CityEntity(
        id = id,
        name = name,
        country = country,
        latitude = latitude.toString(),
        longitude = longitude.toString(),
        isFavorite = false
    )
}

fun CityEntity.toDomain() = with(this) {
    City(
        id = id,
        name = name,
        country = country,
        latitude = latitude.toDouble(),
        longitude = longitude.toDouble()
    )
}

package com.itgonca.citiesapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.itgonca.citiesapp.data.local.db.dao.CityDao
import com.itgonca.citiesapp.data.local.db.entity.CityEntity

@Database(entities = [CityEntity::class], version = 4)
abstract class CityDataBase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}
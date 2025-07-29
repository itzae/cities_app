package com.itgonca.citiesapp.di

import android.content.Context
import androidx.room.Room
import com.itgonca.citiesapp.data.local.db.CityDataBase
import com.itgonca.citiesapp.data.local.db.dao.CityDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDbModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CityDataBase {
        return Room.databaseBuilder(
            context = context.applicationContext,
            klass = CityDataBase::class.java,
            name = "city_database"
        )
            .fallbackToDestructiveMigration(true)
            .build()
    }

    @Singleton
    @Provides
    fun providerCityDao(db: CityDataBase): CityDao = db.cityDao()
}
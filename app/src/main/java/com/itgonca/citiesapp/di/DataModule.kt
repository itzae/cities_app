package com.itgonca.citiesapp.di

import com.itgonca.citiesapp.data.remote.CityRepositoryImpl
import com.itgonca.citiesapp.domain.repository.CityRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun provideCityRepository(impl: CityRepositoryImpl): CityRepository
}
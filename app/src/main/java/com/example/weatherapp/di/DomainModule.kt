package com.example.weatherapp.di

import com.example.domain.repository.Repository
import com.example.domain.usecases.GetCurrentWeatherUseCase
import com.example.domain.usecases.GetDaysWeatherListUseCase
import com.example.domain.usecases.GetHoursWeatherListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {
    @Provides
    fun provideGetCurrentWeatherUseCase(repository: Repository): GetCurrentWeatherUseCase {
        return GetCurrentWeatherUseCase(repository)
    }
    @Provides
    fun provideGetHoursWeatherListUseCase(repository: Repository): GetHoursWeatherListUseCase {
        return GetHoursWeatherListUseCase(repository)
    }
    @Provides
    fun provideGetDaysWeatherListUseCase(repository: Repository): GetDaysWeatherListUseCase {
        return GetDaysWeatherListUseCase(repository)
    }
}
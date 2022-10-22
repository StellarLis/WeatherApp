package com.example.domain.usecases

import com.example.domain.models.WeatherModel
import com.example.domain.repository.Repository

class GetCurrentWeatherUseCase(private val repository: Repository) {

    suspend fun execute(city: String): WeatherModel {
        return repository.getCurrentWeather(city)
    }
}
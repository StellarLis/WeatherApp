package com.example.domain.usecases

import com.example.domain.models.WeatherModel
import com.example.domain.repository.Repository

class GetDaysWeatherListUseCase(private val repository: Repository) {

    suspend fun execute(city: String): List<WeatherModel> {
        return repository.getDaysWeatherList(city)
    }
}
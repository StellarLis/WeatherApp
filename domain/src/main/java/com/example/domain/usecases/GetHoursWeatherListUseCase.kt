package com.example.domain.usecases

import com.example.domain.models.WeatherModel
import com.example.domain.repository.Repository

class GetHoursWeatherListUseCase(private val repository: Repository) {

    suspend fun execute(city: String): List<WeatherModel> {
        return repository.getHoursWeatherList(city)
    }
}
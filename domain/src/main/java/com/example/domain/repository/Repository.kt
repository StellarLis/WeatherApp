package com.example.domain.repository

import com.example.domain.models.WeatherModel

interface Repository {
    suspend fun getCurrentWeather(city: String): WeatherModel
    suspend fun getHoursWeatherList(city: String): List<WeatherModel>
    suspend fun getDaysWeatherList(city: String): List<WeatherModel>
}
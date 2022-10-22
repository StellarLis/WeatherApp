package com.example.data.model

data class WeatherResponseModel(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)
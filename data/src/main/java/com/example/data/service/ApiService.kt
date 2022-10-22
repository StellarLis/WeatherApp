package com.example.data.service

import com.example.data.constance.Constance
import com.example.data.model.WeatherResponseModel
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("v1/forecast.json?key=${Constance.API_KEY}&days=3&aqi=no&alerts=no")
    suspend fun getWeatherResponse(@Query("q") city: String): Response<WeatherResponseModel>
}
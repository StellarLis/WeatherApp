package com.example.data.repository

import com.example.data.service.ApiService
import com.example.domain.models.WeatherModel
import com.example.domain.repository.Repository

class RepositoryImpl(private val retrofit: ApiService) : Repository {
    override suspend fun getCurrentWeather(city: String): WeatherModel {
        val parseableModel = retrofit.getWeatherResponse(city).body()
        val weatherModel = WeatherModel(
            parseableModel?.current?.last_updated?.subSequence(0, 10).toString(),
            parseableModel?.current?.temp_c.toString(),
            parseableModel?.current?.condition?.text ?: "null"
        )
        return weatherModel
    }

    override suspend fun getHoursWeatherList(city: String): List<WeatherModel> {
        val parseableModel = retrofit.getWeatherResponse(city).body()
        val weatherList = ArrayList<WeatherModel>()
        parseableModel?.forecast?.forecastday?.get(0)?.hour?.forEach { hour ->
            val weatherModel = WeatherModel(
                hour.time.subSequence(11, 16).toString(),
                hour.temp_c.toString(),
                hour.condition.text
            )
            weatherList.add(weatherModel)
        }
        return weatherList
    }

    override suspend fun getDaysWeatherList(city: String): List<WeatherModel> {
        val parseableModel = retrofit.getWeatherResponse(city).body()
        val weatherList = ArrayList<WeatherModel>()
        parseableModel?.forecast?.forecastday?.forEach { day ->
            val weatherModel = WeatherModel(
                day.date,
                day.day.avgtemp_c.toString(),
                day.day.condition.text
            )
            weatherList.add(weatherModel)
        }
        return weatherList
    }
}
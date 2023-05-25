package com.example.hw13_weatherapp.repo

import com.example.hw13_weatherapp.model.api.WeatherApiService

class WeatherRemoteDataSource(
    private val weatherApiService: WeatherApiService
) {
    suspend fun getProperties() = weatherApiService.getWeatherResult()
}
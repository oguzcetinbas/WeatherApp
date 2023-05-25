package com.example.hw13_weatherapp.model.api


import com.example.hw13_weatherapp.model.data.WeatherResponse
import retrofit2.http.GET

interface WeatherApiService {

    @GET("v1/forecast?latitude=41.0&longitude=28.9375&current_weather=true&daily=weathercode,apparent_temperature_max,apparent_temperature_min&timezone=auto&temperature_unit=celsius&forecast_days=14")
     suspend fun getWeatherResult() : WeatherResponse?

}
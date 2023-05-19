package com.example.hw13_weatherapp.model.api

import com.example.hw13_weatherapp.constants.Consts
import com.example.hw13_weatherapp.constants.CustomOkHttpClient
import com.example.hw13_weatherapp.model.data.WeatherResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface WeatherApiService {

    @GET("v1/forecast?latitude=41.0&longitude=28.9375&current_weather=true&daily=weathercode,apparent_temperature_max,apparent_temperature_min&timezone=auto&temperature_unit=celsius&forecast_days=14")
     suspend fun getWeatherResult() : WeatherResponse?

    companion object{

        fun create() : WeatherApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(Consts.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(CustomOkHttpClient().onCreate())
                .build()

            return retrofit.create(WeatherApiService::class.java)
        }
    }
}
package com.example.hw13_weatherapp.model.api

import com.example.hw13_weatherapp.constants.Consts
import com.example.hw13_weatherapp.constants.CustomOkHttpClient
import com.example.hw13_weatherapp.constants.MyCustomInterceptor
import com.example.hw13_weatherapp.model.data.WeatherResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface WeatherApiService {

    @GET("v1/forecast?latitude=52.52&longitude=13.41&current_weather=true&daily=weathercode,apparent_temperature_max,apparent_temperature_min&timezone=auto&temperature_unit=celsius&forecast_days=14")
    fun getWeatherResult() : Call<WeatherResponse>

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
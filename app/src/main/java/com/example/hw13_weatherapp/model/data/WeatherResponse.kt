package com.example.hw13_weatherapp.model.data


import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("current_weather")
    val currentWeather: CurrentWeather?,
    @SerializedName("daily")
    val daily: Daily?,
    @SerializedName("daily_units")
    val dailyUnits: DailyUnits?,
    @SerializedName("elevation")
    val elevation: Double?,
    @SerializedName("generationtime_ms")
    val generationtimeMs: Double?,
    @SerializedName("latitude")
    val latitude: Double?,
    @SerializedName("longitude")
    val longitude: Double?,
    @SerializedName("timezone")
    val timezone: String?,
    @SerializedName("timezone_abbreviation")
    val timezoneAbbreviation: String?,
    @SerializedName("utc_offset_seconds")
    val utcOffsetSeconds: Int?,
    var icons: ArrayList<Int>,
)
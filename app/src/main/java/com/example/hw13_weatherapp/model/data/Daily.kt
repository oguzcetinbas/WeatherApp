package com.example.hw13_weatherapp.model.data


import com.google.gson.annotations.SerializedName

data class Daily(
    @SerializedName("apparent_temperature_max")
    val apparentTemperatureMax: List<Double?>?,
    @SerializedName("apparent_temperature_min")
    val apparentTemperatureMin: List<Double?>?,
    @SerializedName("time")
    val time: ArrayList<String?>?,
    @SerializedName("weathercode")
    val weathercode: List<Int?>?
)
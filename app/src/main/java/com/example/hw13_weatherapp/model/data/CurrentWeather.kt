package com.example.hw13_weatherapp.model.data


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "WEATHER_TABLE")
data class CurrentWeather(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("is_day")
    val isDay: Int,

    @SerializedName("temperature")
    val temperature: Double,

    @SerializedName("time")
    val time: String,

    @SerializedName("weathercode")
    val weathercode: Int,

    @SerializedName("winddirection")
    val winddirection: Double,

    @SerializedName("windspeed")
    val windspeed: Double
) : Parcelable
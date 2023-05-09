package com.example.hw13_weatherapp.model.data


import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "weather_property")
data class WeatherResponse(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @SerializedName("current_weather")
    val currentWeather: CurrentWeather,

    @SerializedName("daily")
    val daily: Daily,

    @SerializedName("daily_units")
    val dailyUnits: DailyUnits,

    @SerializedName("elevation")
    val elevation: Double,

    @SerializedName("generationtime_ms")
    val generationtimeMs: Double,

    @SerializedName("latitude")
    val latitude: Double,

    @SerializedName("longitude")
    val longitude: Double,

    @SerializedName("timezone")
    val timezone: String?,

    @SerializedName("timezone_abbreviation")
    val timezoneAbbreviation: String?,

    @SerializedName("utc_offset_seconds")
    val utcOffsetSeconds: Int,

    var icons: ArrayList<Int>
):Parcelable
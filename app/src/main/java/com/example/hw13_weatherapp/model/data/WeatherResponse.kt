package com.example.hw13_weatherapp.model.data


import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

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
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        TODO("currentWeather"),
        TODO("daily"),
        TODO("dailyUnits"),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        TODO("icons")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeDouble(elevation)
        parcel.writeDouble(generationtimeMs)
        parcel.writeDouble(latitude)
        parcel.writeDouble(longitude)
        parcel.writeString(timezone)
        parcel.writeString(timezoneAbbreviation)
        parcel.writeInt(utcOffsetSeconds)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WeatherResponse> {
        override fun createFromParcel(parcel: Parcel): WeatherResponse {
            return WeatherResponse(parcel)
        }

        override fun newArray(size: Int): Array<WeatherResponse?> {
            return arrayOfNulls(size)
        }
    }
}
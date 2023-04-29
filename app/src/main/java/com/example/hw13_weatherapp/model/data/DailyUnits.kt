package com.example.hw13_weatherapp.model.data


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "daily_")
@Parcelize
data class DailyUnits(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("apparent_temperature_max")
    val apparentTemperatureMax: String,
    @SerializedName("apparent_temperature_min")
    val apparentTemperatureMin: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("weathercode")
    val weathercode: String
):Parcelable
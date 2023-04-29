package com.example.hw13_weatherapp.model.data


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "table2")
@Parcelize
data class Daily(

    @PrimaryKey(autoGenerate = false)
    @SerializedName("apparent_temperature_max")
    val apparentTemperatureMax: List<Double?>,
    @SerializedName("apparent_temperature_min")
    val apparentTemperatureMin: List<Double?>,
    @SerializedName("time")
    val time: List<String?>,
    @SerializedName("weathercode")
    val weathercode: List<Int?>
):Parcelable
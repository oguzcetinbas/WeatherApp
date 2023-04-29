package com.example.hw13_weatherapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hw13_weatherapp.model.data.WeatherResponse
@Dao
interface WeatherPropertyDao  {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProperties(properties: List<WeatherResponse>)

    @Query("SELECT * FROM weather_property")
    fun getAllProperties(): List<WeatherResponse>



}
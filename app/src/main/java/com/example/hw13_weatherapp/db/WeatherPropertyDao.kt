package com.example.hw13_weatherapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hw13_weatherapp.model.data.WeatherResponse
@Dao
interface WeatherPropertyDao  {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProperties(properties: WeatherResponse)

    @Query("SELECT * FROM weather_property")
    fun getAllProperties(): List<WeatherResponse>

    @Delete
    fun deleteItem(item: WeatherResponse)

    @Query("DELETE FROM weather_property")
    fun deleteAllItem()




}
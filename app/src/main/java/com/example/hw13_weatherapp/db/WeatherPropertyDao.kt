package com.example.hw13_weatherapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hw13_weatherapp.model.data.WeatherResponse
@Dao
interface WeatherPropertyDao  {

    @Query("SELECT * FROM weather_property")
     fun getAll(): WeatherResponse

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(weatherResponse: WeatherResponse)

    @Query("DELETE FROM weather_property")
     fun deleteAllItem()

}
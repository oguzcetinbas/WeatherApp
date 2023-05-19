package com.example.hw13_weatherapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hw13_weatherapp.model.data.WeatherResponse

@Dao
interface WeatherPropertyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToRoomDb(weatherResponse: WeatherResponse?)

    @Query("SELECT * FROM weather_property")
    fun getAllFromRoomDb(): WeatherResponse

    @Query("DELETE FROM weather_property")
    fun deleteAllItem()

}
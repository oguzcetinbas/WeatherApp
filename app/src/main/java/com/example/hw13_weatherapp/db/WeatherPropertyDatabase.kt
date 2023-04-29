package com.example.hw13_weatherapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.hw13_weatherapp.model.data.CurrentWeather
import com.example.hw13_weatherapp.model.data.Daily
import com.example.hw13_weatherapp.model.data.DailyUnits
import com.example.hw13_weatherapp.model.data.WeatherResponse
import com.example.hw13_weatherapp.utils.DatabaseConverter

@Database(entities = [WeatherResponse::class, Daily::class, DailyUnits::class, CurrentWeather::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseConverter::class)
abstract class WeatherPropertyDatabase : RoomDatabase() {

    abstract fun weatherPropertyDao(): WeatherPropertyDao

}
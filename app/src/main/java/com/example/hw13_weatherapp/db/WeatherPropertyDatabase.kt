package com.example.hw13_weatherapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.hw13_weatherapp.model.data.WeatherResponse

@Database(entities = [WeatherResponse::class], version = 1)
abstract class WeatherPropertyDatabase : RoomDatabase() {

    abstract fun weatherPropertyDao(): WeatherPropertyDao

}
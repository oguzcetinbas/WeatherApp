package com.example.hw13_weatherapp.repo

import android.content.Context
import androidx.room.Room
import com.example.hw13_weatherapp.db.WeatherPropertyDao
import com.example.hw13_weatherapp.db.WeatherPropertyDatabase
import com.example.hw13_weatherapp.model.data.WeatherResponse
import java.lang.Exception
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

object WeatherPropertyRepository {

    private lateinit var weatherPropertyDao: WeatherPropertyDao
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    fun initialize(context: Context) {

        val weatherPropertyDatabase = Room.databaseBuilder(
            context.applicationContext,
            WeatherPropertyDatabase::class.java,
            "name_property_database",
        ).build()

        weatherPropertyDao = weatherPropertyDatabase.weatherPropertyDao()
    }

    fun insertProperties(properties: WeatherResponse, callback: (succes: Boolean) -> Unit) {
        executor.execute {
            try {
                weatherPropertyDao.insert(properties)
                callback(true)
            } catch (e: Exception) {
                e.printStackTrace()
                callback(false)
            }
        }
    }
}
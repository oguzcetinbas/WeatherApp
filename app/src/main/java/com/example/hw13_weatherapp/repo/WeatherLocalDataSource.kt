package com.example.hw13_weatherapp.repo

import com.example.hw13_weatherapp.db.WeatherPropertyDao
import com.example.hw13_weatherapp.model.data.WeatherResponse

class WeatherLocalDataSource(
    private val weatherPropertyDao: WeatherPropertyDao
) {
    fun insertProperties(properties: WeatherResponse?) {
        weatherPropertyDao.insertToRoomDb(properties)
    }

    fun getAllPropertiesFromRoomDb() = weatherPropertyDao.getAllFromRoomDb()
}
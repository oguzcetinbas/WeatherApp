package com.example.hw13_weatherapp.repo

import android.content.Context
import androidx.room.Room
import com.example.hw13_weatherapp.constants.Consts
import com.example.hw13_weatherapp.db.WeatherPropertyDao
import com.example.hw13_weatherapp.db.WeatherPropertyDatabase
import com.example.hw13_weatherapp.model.api.WeatherApiService
import com.example.hw13_weatherapp.model.data.WeatherResponse
import com.example.hw13_weatherapp.network.NetworkUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse
import java.lang.Exception

class WeatherPropertyRepository(private val context: Context) {

    private val weatherApiService = WeatherApiService.create()
    private lateinit var weatherPropertyDao: WeatherPropertyDao

    init {
        val weatherPropertyDatabase = Room.databaseBuilder(
            context.applicationContext,
            WeatherPropertyDatabase::class.java,
            "name_property_database"
        ).build()

        weatherPropertyDao = weatherPropertyDatabase.weatherPropertyDao()
    }

    suspend fun fetchProperties(): WeatherResponse {
        if (NetworkUtil.isInternetAvailable(context)) {
            return fetchFromService()
            println("Gelen data servisten")
        } else {
            return fetchFromDatabase()
            println("Gelen data database'den")
        }
    }

    private suspend fun fetchFromService(): WeatherResponse {
        val response = weatherApiService.getWeatherResult().awaitResponse()
        if (response.isSuccessful) {
            val responseList = response.body()
            if (responseList != null) {
                insertProperties(responseList)
                setIcons(responseList)
                return responseList
            }
        }
        return fetchFromDatabase()
    }

    private suspend fun fetchFromDatabase(): WeatherResponse {
        return withContext(Dispatchers.IO) {
            weatherPropertyDao.getAll()
        }
    }

    private suspend fun insertProperties(properties: WeatherResponse) {
        withContext(Dispatchers.IO) {
            try {
                weatherPropertyDao.insert(properties)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun setIcons(weatherResponse: WeatherResponse?) {

        val weatherCodes = weatherResponse?.daily?.weathercode

        val icons = ArrayList<Int>()

        weatherCodes?.forEach {
            when (it) {
                0 -> icons.add(Consts.GUNES)
                in 1..3 -> icons.add(Consts.PARCA)
                45, 48 -> icons.add(Consts.SIS)
                51, 53, 55, 56, 57 -> icons.add(Consts.GUNES)
                61, 63, 65, 66, 67 -> icons.add(Consts.YAGMUR)
                71, 73, 75, 77 -> icons.add(Consts.YAGMUR)
                80, 81, 82 -> icons.add(Consts.PARCA)
                85, 86 -> icons.add(Consts.FIRTINA)
                95 -> icons.add(Consts.KAR)
                96, 99 -> icons.add(Consts.KAR)
            }
        }
        weatherResponse?.icons = icons
    }
}
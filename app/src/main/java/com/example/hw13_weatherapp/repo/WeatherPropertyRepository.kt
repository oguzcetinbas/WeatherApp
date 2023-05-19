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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call

class WeatherPropertyRepository(
    private val context: Context,
    private val weatherApiService: WeatherApiService
) {

    private lateinit var weatherPropertyDao: WeatherPropertyDao

    init {
        val weatherPropertyDatabase = Room.databaseBuilder(
            context.applicationContext,
            WeatherPropertyDatabase::class.java,
            "name_property_database"
        ).fallbackToDestructiveMigration()
            .build()

        weatherPropertyDao = weatherPropertyDatabase.weatherPropertyDao()
    }

    fun insertProperties(properties: WeatherResponse?) {
        weatherPropertyDao.insertToRoomDb(properties)
    }

    fun getAllProperties(): Flow<WeatherResponse?> = flow {
        if (NetworkUtil.isInternetAvailable(context)) {
            val propertiesFromApi = weatherApiService.getWeatherResult()
            setIcons(propertiesFromApi)
            insertProperties(propertiesFromApi)
            emit(propertiesFromApi)
        }else {
            emit(weatherPropertyDao.getAllFromRoomDb())
        }
    }.flowOn(Dispatchers.IO)

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
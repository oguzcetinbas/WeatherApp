package com.example.hw13_weatherapp.repo

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.hw13_weatherapp.constants.Consts
import com.example.hw13_weatherapp.db.WeatherPropertyDao
import com.example.hw13_weatherapp.db.WeatherPropertyDatabase
import com.example.hw13_weatherapp.model.api.WeatherApiService
import com.example.hw13_weatherapp.model.data.WeatherResponse
import com.example.hw13_weatherapp.util.NetworkUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class WeatherPropertyRepository(private val context: Context) {

    private val weatherApiService = WeatherApiService.create()
    private lateinit var weatherPropertyDao: WeatherPropertyDao
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val weatherPropertyDatabase = Room.databaseBuilder(
            context.applicationContext,
            WeatherPropertyDatabase::class.java,
            "name_property_database"
        ).build()

        weatherPropertyDao = weatherPropertyDatabase.weatherPropertyDao()
    }
    fun fetchProperties(callback: (properties: WeatherResponse) -> Unit) {
        if (NetworkUtil.isInternetAvailable(context)) {
                fetchFromService(callback)
                println("Gelen data servisten")
        }else {
            fetchFromDatabase(callback)
            println("Gelen data database'den")
        }
    }
    private fun fetchFromService(callback: (properties: WeatherResponse) -> Unit ) {
        weatherApiService.getWeatherResult().enqueue(object : Callback<WeatherResponse> {

            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.isSuccessful) {
                    val responseList = response.body()
                    if (responseList != null) {
                        callback(responseList)
                        insertProperties(responseList)
                    }
                }else {
                    fetchFromDatabase(callback)
                }

               setIcons(response.body())
            }
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                fetchFromDatabase(callback)
            }
        })
    }
    private fun fetchFromDatabase(callback: (properties: WeatherResponse) -> Unit) {
        executor.execute {
            val properties = weatherPropertyDao.getAll()
            callback(properties)
        }
    }
    fun insertProperties(properties: WeatherResponse) {
        executor.execute {
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
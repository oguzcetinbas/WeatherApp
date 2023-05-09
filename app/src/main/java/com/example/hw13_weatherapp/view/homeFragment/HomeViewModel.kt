package com.example.hw13_weatherapp.view.homeFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw13_weatherapp.constants.Consts
import com.example.hw13_weatherapp.model.data.WeatherResponse
import com.example.hw13_weatherapp.repo.WeatherPropertyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val weatherPropertyRepository: WeatherPropertyRepository) :
    ViewModel() {

    private val _weatherData = MutableLiveData<WeatherResponse?>()
    val weatherData: LiveData<WeatherResponse?> = _weatherData

    init {
        fetchProperties()
    }

    private fun fetchProperties() {
        weatherPropertyRepository.fetchProperties { properties ->
            _weatherData.postValue(properties)
        }
    }

//    fun getDataFromServiceWithCoroutines() {
//        viewModelScope.launch(Dispatchers.IO) {
//            weatherPropertyRepository.getResponseInRepository()
//        }
//    }
}
package com.example.hw13_weatherapp.view.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hw13_weatherapp.repo.WeatherPropertyRepository
import com.example.hw13_weatherapp.view.homeFragment.HomeViewModel

class HomeViewModelFactory(private val weatherPropertyRepository: WeatherPropertyRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(viewModelClass: Class<T>): T {
        if (viewModelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(weatherPropertyRepository) as T
        }
        throw IllegalArgumentException("Unknow View Model class ")
    }
}
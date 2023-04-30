package com.example.hw13_weatherapp.view.homeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.hw13_weatherapp.databinding.FragmentHomeBinding
import com.example.hw13_weatherapp.model.data.WeatherResponse
import com.example.hw13_weatherapp.repo.WeatherPropertyRepostory

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)

        context?.let { WeatherPropertyRepostory.initialize(it) }


        if (viewModel.weatherData.value == null) {
            viewModel.getDataService()
        }
        initObserve()
        return binding.root
    }

    fun initObserve() {
        viewModel.weatherData.observe(viewLifecycleOwner) {
            initRecyclerView(it)
        }
    }

    private fun initRecyclerView(weatherResponse: WeatherResponse?) {

        val adapter = WeatherDataAdapter(weatherResponse ?: throw IllegalAccessError(""))
        binding.recyclerView.adapter = adapter
    }
}
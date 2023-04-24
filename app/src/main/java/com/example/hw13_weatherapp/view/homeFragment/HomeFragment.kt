package com.example.hw13_weatherapp.view.homeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.hw13_weatherapp.R
import com.example.hw13_weatherapp.databinding.CurrentDayItemBinding
import com.example.hw13_weatherapp.databinding.FragmentHomeBinding
import com.example.hw13_weatherapp.model.data.WeatherResponse
import java.text.DateFormat
import java.util.Calendar

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)


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
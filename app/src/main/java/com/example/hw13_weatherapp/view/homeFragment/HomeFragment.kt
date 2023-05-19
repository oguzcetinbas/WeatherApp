package com.example.hw13_weatherapp.view.homeFragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hw13_weatherapp.databinding.FragmentHomeBinding
import com.example.hw13_weatherapp.model.api.WeatherApiService
import com.example.hw13_weatherapp.model.data.WeatherResponse
import com.example.hw13_weatherapp.repo.WeatherPropertyRepository
import com.example.hw13_weatherapp.utils.sendNotification
import com.example.hw13_weatherapp.view.factory.HomeViewModelFactory

class HomeFragment : Fragment(){

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)

        val weatherApiService = WeatherApiService.create()
        val weatherPropertyRepository = WeatherPropertyRepository(requireContext(),weatherApiService)
        viewModel = ViewModelProvider(
            this,
            HomeViewModelFactory(weatherPropertyRepository)
        )[HomeViewModel::class.java]

        initObserve()
        return binding.root
    }

    fun initObserve() {
        viewModel.properties.observe(viewLifecycleOwner) {
            initRecyclerView(it)
            NotificationManagerCompat.from(requireContext()).sendNotification("Current Weather", it?.currentWeather?.temperature.toString(), requireContext())
        }
    }
    private fun initRecyclerView(weatherResponse : WeatherResponse?) {

        val adapters = weatherResponse?.let {weatherRes ->
            WeatherDataAdapter(weatherRes) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSecondFragment(weatherRes,it))
            }
        }
        binding.recyclerView.adapter = adapters
    }



}
package com.example.hw13_weatherapp.view.homeFragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hw13_weatherapp.databinding.FragmentHomeBinding
import com.example.hw13_weatherapp.model.data.WeatherResponse
import com.example.hw13_weatherapp.utils.sendNotification
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(){

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)

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
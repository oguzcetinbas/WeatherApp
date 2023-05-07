package com.example.hw13_weatherapp.view.homeFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.hw13_weatherapp.constants.addCelcius
import com.example.hw13_weatherapp.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    private val args : SecondFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater)


        val weatherData = args.weatherRes
        val position = args.position

        binding.apply {
            title.text = weatherData?.daily?.time?.get(position)
            weatherData?.icons.let {
                weatherIcon.setImageResource(it?.get(position) ?: position)
            }
            tvTemperature.text = weatherData?.daily?.apparentTemperatureMax?.get(position).toString().addCelcius()
        }

        return binding.root
    }

}

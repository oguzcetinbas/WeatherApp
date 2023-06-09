package com.example.hw13_weatherapp.view.homeFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hw13_weatherapp.R
import com.example.hw13_weatherapp.constants.Consts
import com.example.hw13_weatherapp.constants.addCelcius
import com.example.hw13_weatherapp.constants.addSpeedText
import com.example.hw13_weatherapp.databinding.CurrentDayItemBinding
import com.example.hw13_weatherapp.databinding.NextDaysItemBinding
import com.example.hw13_weatherapp.model.data.WeatherResponse

class WeatherDataAdapter(private val weatherResponse: WeatherResponse, val onClick: (Int) -> Unit) :
    Adapter<WeatherDataAdapter.WeatherDataViewHolder>() {

    private val currentWeather = weatherResponse.currentWeather
    private val times = weatherResponse.daily.time
    private val maxTemps = weatherResponse.daily.apparentTemperatureMax
    private val minTemps = weatherResponse.daily.apparentTemperatureMin
    private val icons = weatherResponse.icons


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherDataAdapter.WeatherDataViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        val view = when (viewType) {
            Consts.VIEW_TYPE_CURRENT_DAY -> layoutInflater.inflate(
                R.layout.current_day_item,
                parent,
                false
            )

            Consts.VIEW_TYPE_NEXTDAYS -> layoutInflater.inflate(
                R.layout.next_days_item,
                parent,
                false
            )

            else -> throw IllegalArgumentException()
        }
        return WeatherDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherDataAdapter.WeatherDataViewHolder, position: Int) {

        holder.bind(
            time = times[position],
            maxTemp = maxTemps[position],
            minTemp = minTemps[position],
            icon = icons[position]
        )
    }

    override fun getItemCount(): Int {
        return times.size
    }

    override fun getItemViewType(position: Int): Int {

        return if (position == 0) {
            Consts.VIEW_TYPE_CURRENT_DAY
        } else
            Consts.VIEW_TYPE_NEXTDAYS
    }

    inner class WeatherDataViewHolder(itemView: View) : ViewHolder(itemView) {
        fun bind(time: String?, maxTemp: Double?, minTemp: Double?, icon: Int) {

            if (adapterPosition == Consts.VIEW_TYPE_CURRENT_DAY) {
                val binding = CurrentDayItemBinding.bind(itemView)
                binding.apply {
                    tvTemperature.text = currentWeather.temperature.toString().addCelcius()
                    tvWindSpeed.text = currentWeather.windspeed.toString().addSpeedText()
                    tvWindDirection.text = currentWeather.winddirection.toString()
                    ivWeatherIcon.setImageResource(icon)
                }
            } else {
                val binding = NextDaysItemBinding.bind(itemView)
                binding.apply {
                    tvDate.text = time
                    tvMinTemp.text = minTemp.toString().addCelcius()
                    tvMaxTemp.text = maxTemp.toString().addCelcius()
                    ivWeatherIcon.setImageResource(icon)

                    root.setOnClickListener {
                        onClick(adapterPosition)
                    }
                }
            }
        }
    }
}
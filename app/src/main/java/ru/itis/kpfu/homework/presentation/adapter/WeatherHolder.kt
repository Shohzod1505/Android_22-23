package ru.itis.kpfu.homework.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import ru.itis.kpfu.homework.databinding.ItemWeatherBinding
import ru.itis.kpfu.homework.domain.weather.WeatherInfo
import ru.itis.kpfu.homework.presentation.WeatherUi

class WeatherHolder(
    private val binding: ItemWeatherBinding,
    private val action: (WeatherInfo?) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    private val weatherUi = WeatherUi()

    fun onBind(weatherInfo: WeatherInfo?) {
        with(binding) {

            tvCityName.text = weatherInfo?.name
            weatherUi.showTemp(tvCityTemp, weatherInfo?.temperature)
            weatherUi.showWeatherIcon(ivWeatherIcon, weatherInfo?.icon)

            root.setOnClickListener { 
                action(weatherInfo)
            }
        }
    }

}

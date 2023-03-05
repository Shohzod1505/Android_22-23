package ru.itis.kpfu.homework.adapter

import androidx.recyclerview.widget.RecyclerView
import ru.itis.kpfu.homework.data.response.WeatherResponse
import ru.itis.kpfu.homework.databinding.ItemWeatherBinding
import ru.itis.kpfu.homework.ui.WeatherUi

class WeatherHolder(
    private val binding: ItemWeatherBinding,
) : RecyclerView.ViewHolder(binding.root) {

    private val weatherUi = WeatherUi()

    fun onBind(weatherResponse: WeatherResponse) {
        with(binding) {
            val temp = weatherResponse.main.temp

            tvCityName.text = weatherResponse.name
            weatherUi.showTemp(tvCityTemp, temp)
            weatherResponse.weather.firstOrNull()?.also {
                weatherUi.showWeatherIcon(ivWeatherIcon, it.icon)
            }
        }
    }

}

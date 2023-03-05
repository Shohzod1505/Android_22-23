package ru.itis.kpfu.homework.ui;

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ru.itis.kpfu.homework.R
import ru.itis.kpfu.homework.data.DataContainer
import ru.itis.kpfu.homework.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private var binding: FragmentDetailBinding? = null
    private val api = DataContainer.weatherApi
    private val weatherUi = WeatherUi()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)

        binding?.run {
            val query = arguments?.getString(ARG_QUERY)
            tvCityName.text = query
            loadWeather(query)

            btBack.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, SearchFragment())
                    .addToBackStack("DetailFragment")
                    .commit()
            }

        }

    }

    private fun loadWeather(query: String?) {
        lifecycleScope.launch {
            api.getWeather(query).also {
                showTemp(it.main.temp)
                it.weather.firstOrNull()?.also {
                    showWeatherIcon(it.icon)
                }
                showHumidity(it.main.humidity)
                showHumidityIcon(it.main.humidity)
                showWindDirection(it.wind.deg)
                showWindDirectionIcon(it.wind.deg)
            }
        }
    }

    private fun showWeatherIcon(id: String) {
        weatherUi.showWeatherIcon(binding?.ivWeatherIcon, id)
    }

    private fun showHumidityIcon(humidity: Int) {
        weatherUi.showHumidityIcon(binding?.ivHumidityIcon, humidity)
    }

    private fun showWindDirectionIcon(degree: Int) {
        weatherUi.showWindDirectionIcon(binding?.ivDirectionIcon, degree)
    }

    private fun showTemp(temp: Double) {
        weatherUi.showTemp(binding?.tvCityTemp, temp)
    }

    private fun showHumidity(humidity: Int) {
        weatherUi.showHumidity(binding?.tvCityHumidity, humidity)
    }

    private fun showWindDirection(degree: Int) {
        weatherUi.showWindDirection(binding?.tvCityWind, degree)
    }

    companion object {
        private const val ARG_QUERY = "ARG_QUERY"

        fun newInstanceQuery(query: String?, flag: Boolean) = DetailFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_QUERY, query)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}

package ru.itis.kpfu.homework.presentation.screens;

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ru.itis.kpfu.homework.R
import ru.itis.kpfu.homework.di.DataContainer
import ru.itis.kpfu.homework.databinding.FragmentDetailBinding
import ru.itis.kpfu.homework.domain.weather.GetWeatherByCoordUseCase
import ru.itis.kpfu.homework.domain.weather.GetWeatherByNameUseCase
import ru.itis.kpfu.homework.presentation.WeatherUi

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private var binding: FragmentDetailBinding? = null
    private val getWeatherByNameUseCase: GetWeatherByNameUseCase = DataContainer.weatherByNameUseCase
    private val getWeatherByCoordUseCase: GetWeatherByCoordUseCase = DataContainer.weatherByCoordUseCase
    private val weatherUi = WeatherUi()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)

        binding?.run {
            val query = arguments?.getString(ARG_QUERY)
            val lat = arguments?.getDouble(ARG_LAT)
            val lon = arguments?.getDouble(ARG_LON)
            val flag = arguments?.getBoolean(ARG_FLAG)

            if (flag == true) {
                loadWeather(query)
                tvCityName.text = query
            } else {
                loadWeather(lat, lon)
            }

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
            getWeatherByNameUseCase(query).also {
                showTemp(it.temperature)
                showWeatherIcon(it.icon)
                showHumidity(it.humidity)
                showHumidityIcon(it.humidity)
                showWindDirection(it.windDegree)
                showWindDirectionIcon(it.windDegree)
            }
        }
    }

    private fun loadWeather(lat: Double?, lon: Double?) {
        lifecycleScope.launch {
            getWeatherByCoordUseCase(lat, lon).also {
                binding?.tvCityName?.text = it.name
                showTemp(it.temperature)
                showWeatherIcon(it.icon)
                showHumidity(it.humidity)
                showHumidityIcon(it.humidity)
                showWindDirection(it.windDegree)
                showWindDirectionIcon(it.windDegree)
            }
        }
    }


    private fun showWeatherIcon(id: String?) {
        weatherUi.showWeatherIcon(binding?.ivWeatherIcon, id)
    }

    private fun showHumidityIcon(humidity: Int) {
        weatherUi.showHumidityIcon(binding?.ivHumidityIcon, humidity)
    }

    private fun showWindDirectionIcon(degree: Int) {
        weatherUi.showWindDirectionIcon(binding?.ivDirectionIcon, degree)
    }

    private fun showTemp(temp: Double?) {
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
        private const val ARG_LAT = "ARG_LAT"
        private const val ARG_LON = "ARG_LON"
        private const val ARG_FLAG = "ARG_FLAG"

        fun newInstanceQuery(query: String?, flag: Boolean) = DetailFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_QUERY, query)
                putBoolean(ARG_FLAG, flag)
            }
        }

        fun newInstanceCoord(lat: Double?, lon: Double?, flag: Boolean) = DetailFragment().apply {
            arguments = Bundle().apply {
                lat?.let { putDouble(ARG_LAT, it) }
                lon?.let { putDouble(ARG_LON, it) }
                putBoolean(ARG_FLAG, flag)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}

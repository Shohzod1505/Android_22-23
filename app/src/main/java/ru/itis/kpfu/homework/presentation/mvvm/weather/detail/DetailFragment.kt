package ru.itis.kpfu.homework.presentation.mvvm.weather.detail;

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.itis.kpfu.homework.App
import ru.itis.kpfu.homework.R
import ru.itis.kpfu.homework.databinding.FragmentDetailBinding
import ru.itis.kpfu.homework.presentation.mvvm.weather.WeatherUi
import ru.itis.kpfu.homework.presentation.mvvm.weather.search.SearchFragment

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private var binding: FragmentDetailBinding? = null
    private val weatherUi = WeatherUi()
    private val viewModel: DetailViewModel by viewModels {
        DetailViewModel.Factory
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        App.appComponent.plusDetailComponent()
//            .build()
//            .inject(this)
//        super.onCreate(savedInstanceState)
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)

        observeViewModel()

        binding?.run {
            val query = arguments?.getString(ARG_QUERY)
            val lat = arguments?.getDouble(ARG_LAT)
            val lon = arguments?.getDouble(ARG_LON)
            val flag = arguments?.getBoolean(ARG_FLAG)

            if (flag == true) {
                viewModel.loadWeather(query)
            } else {
                viewModel.loadWeather(lat, lon)
            }

            btBack.setOnClickListener {
                navigate()
            }
        }
    }

    private fun observeViewModel() {
        with(viewModel) {
            weatherInfo.observe(viewLifecycleOwner) {
                if (it == null) return@observe
                binding?.tvCityName?.text = it.name
                showTemp(it.temperature)
                showWeatherIcon(it.icon)
                showHumidity(it.humidity)
                showHumidityIcon(it.humidity)
                showWindDirection(it.windDegree)
                showWindDirectionIcon(it.windDegree)
            }

            navigation.observe(viewLifecycleOwner) {
                if (it == true) {
                    navigate()
                    navigation.value = null
                }
            }
        }
    }

    private fun navigate() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SearchFragment())
            .addToBackStack("DetailFragment")
            .commit()
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

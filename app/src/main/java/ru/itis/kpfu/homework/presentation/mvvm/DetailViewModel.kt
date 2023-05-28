package ru.itis.kpfu.homework.presentation.mvvm

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.launch
import ru.itis.kpfu.homework.di.DataContainer
import ru.itis.kpfu.homework.domain.weather.GetWeatherByCoordUseCase
import ru.itis.kpfu.homework.domain.weather.GetWeatherByNameUseCase
import ru.itis.kpfu.homework.domain.weather.WeatherInfo

class DetailViewModel(
    private val getWeatherByNameUseCase: GetWeatherByNameUseCase,
    private val getWeatherByCoordUseCase: GetWeatherByCoordUseCase,
): ViewModel() {

    private val _weatherInfo = MutableLiveData<WeatherInfo?>(null)
    val weatherInfo: LiveData<WeatherInfo?>
        get() = _weatherInfo

    val navigation = MutableLiveData<Boolean>(null)

    fun loadWeather(query: String?) {
        viewModelScope.launch {
            getWeatherByNameUseCase(query).also { weatherInfo ->
                _weatherInfo.value = weatherInfo
            }
        }
    }

    fun loadWeather(lat: Double?, lon: Double?) {
        viewModelScope.launch {
            getWeatherByCoordUseCase(lat, lon).also { weatherInfo ->
                _weatherInfo.value = weatherInfo
            }
        }
    }

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val weatherByNameUseCase = DataContainer.weatherByNameUseCase
                val weatherByCoordUseCase = DataContainer.weatherByCoordUseCase
                // val savedStateHandle = extras.createSavedStateHandle()
                return DetailViewModel(weatherByNameUseCase, weatherByCoordUseCase) as T
            }
        }

        val FactoryExt: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val weatherByNameUseCase = DataContainer.weatherByNameUseCase
                val weatherByCoordUseCase = DataContainer.weatherByCoordUseCase
                // val savedStateHandle = extras.createSavedStateHandle()
                DetailViewModel(weatherByNameUseCase, weatherByCoordUseCase)
            }
        }

    }

}

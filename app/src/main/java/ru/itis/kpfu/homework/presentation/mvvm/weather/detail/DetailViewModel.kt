package ru.itis.kpfu.homework.presentation.mvvm.weather.detail

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.launch
import ru.itis.kpfu.homework.domain.weather.GetWeatherByCoordUseCase
import ru.itis.kpfu.homework.domain.weather.GetWeatherByNameUseCase
import ru.itis.kpfu.homework.domain.weather.WeatherInfo
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val getWeatherByNameUseCase: GetWeatherByNameUseCase,
    private val getWeatherByCoordUseCase: GetWeatherByCoordUseCase,
//    private val savedState: SavedStateHandle,
): ViewModel() {

//    init {
//        savedState.getLiveData<String>("query")
//    }

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

        fun factory(
            weatherByNameUseCase: GetWeatherByNameUseCase,
            weatherByCoordUseCase: GetWeatherByCoordUseCase,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
//                val savedStateHandle = extras.createSavedStateHandle()
                return DetailViewModel(
                    weatherByNameUseCase,
                    weatherByCoordUseCase) as T
            }
        }
    }

}

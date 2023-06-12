package ru.itis.kpfu.homework.presentation.mvvm.weather.detail

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.itis.kpfu.homework.domain.weather.GetWeatherByCoordUseCase
import ru.itis.kpfu.homework.domain.weather.GetWeatherByNameUseCase
import ru.itis.kpfu.homework.domain.weather.WeatherInfo
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val getWeatherByNameUseCase: GetWeatherByNameUseCase,
    private val getWeatherByCoordUseCase: GetWeatherByCoordUseCase,
): ViewModel() {

    private val _weatherInfo = MutableLiveData<WeatherInfo?>(null)
    val weatherInfo: LiveData<WeatherInfo?>
        get() = _weatherInfo

    val weatherInfoFlow: StateFlow<WeatherInfo?> = flowOf("Kazan")
        .mapLatest {
            getWeatherByNameUseCase(it)
        }.stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    private val _state: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.Initial)
    val state = _state.asStateFlow()

    val navigation = MutableLiveData<Boolean>(null)

    fun loadWeather(query: String?) {
        viewModelScope.launch {
            try {
                getWeatherByNameUseCase(query).also { weatherInfo ->
                    _weatherInfo.value = weatherInfo
                }
            } catch (error: Throwable) {

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

    fun reducer(event: ScreenEvent) {
        when(event) {
            is ScreenEvent.OnQueryChange -> onQueryChange(event.query)
            is ScreenEvent.OnCreate -> onCreate(event.id)
        }
    }

    private fun onQueryChange(query: String) {
        viewModelScope.launch {
            try {
                getWeatherByNameUseCase(query).also { weatherInfo ->
                    _state.emit(ScreenState.SuccessData(weatherInfo))
                }
            } catch (error: Throwable) {

            }
        }
    }

    private fun onCreate(id: Int) {

    }

    sealed interface ScreenEvent {
        data class OnQueryChange(val query: String): ScreenEvent
        data class OnCreate(val id: Int): ScreenEvent
    }

    sealed interface ScreenState {
        object Initial: ScreenState
        data class Error(val throwable: Throwable): ScreenState
        data class SuccessData(val weatherInfo: WeatherInfo): ScreenState
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

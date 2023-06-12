package ru.itis.kpfu.homework.presentation.mvvm.weather.search

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.itis.kpfu.homework.data.weather.datasource.local.entity.Weather
import ru.itis.kpfu.homework.domain.weather.*
import ru.itis.kpfu.homework.presentation.mvvm.weather.detail.DetailViewModel
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val getWeatherByNameUseCase: GetWeatherByNameUseCase,
    private val getWeatherByCoordUseCase: GetWeatherByCoordUseCase,
    private val saveWeatherUseCase: SaveWeatherUseCase,
    private val deleteWeatherUseCase: DeleteWeatherUseCase,
    private val findWeatherByNameUseCase: FindWeatherByNameUseCase,
//    private val resourceProvider: ResourceProvider,
): ViewModel() {

    private val _state: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.Initial)
    val state = _state.asStateFlow()

    private val _stateData: MutableStateFlow<ScreenStateData> = MutableStateFlow(ScreenStateData())
    val stateData = _stateData.asStateFlow()

    private val _command: MutableStateFlow<ScreenCommand> = MutableStateFlow(ScreenCommand.Initial)
    val command = _command.asStateFlow()

    val navigationName = MutableLiveData<String?>(null)

    val navigationCoord = MutableLiveData<DoubleArray?>(null)

    suspend fun saveWeather(weather: Weather) {
        saveWeatherUseCase(weather)
    }

    suspend fun deleteWeather(weather: Weather) {
        deleteWeatherUseCase(weather)
    }

    suspend fun findWeatherByName(name: String): Weather? = findWeatherByNameUseCase(name)

    suspend fun getWeatherByName(query: String?) = getWeatherByNameUseCase(query)

    suspend fun getWeatherByCoord(lat: Double?, lon: Double?) = getWeatherByCoordUseCase(lat, lon)

    fun reducer(event: ScreenEvent) {
        when (event) {
            is ScreenEvent.OnQueryChangeName -> onQueryChangeName(event.query)
            is ScreenEvent.OnQueryChangeCoord -> onQueryChangeCoord(event.lat, event.lon)
            is ScreenEvent.OnCreate -> onCreate(event.id)
        }
    }

    private fun onCreate(id: Int) {

    }

    private fun onQueryChangeName(query: String) {
        viewModelScope.launch {
            try {
                _state.emit(ScreenState.Loading(true))

                _stateData.emit(_stateData.value.copy(
                    isLoading = true
                ))

                getWeatherByNameUseCase(query)
                navigationName.value = query
            } catch (error: Throwable) {
                _state.emit(ScreenState.Error("City not found!"))

                _stateData.emit(_stateData.value.copy(
                    error = "City not found!"
                ))
            } finally {
                _state.emit(ScreenState.Loading(false))

                _stateData.emit(_stateData.value.copy(
                    isLoading = false
                ))
            }
        }
    }

    private fun onQueryChangeCoord(lat: Double?, lon: Double?) {
        viewModelScope.launch {
            try {
                _state.emit(ScreenState.Loading(true))
                getWeatherByCoordUseCase(lat, lon)
                if (lat != null && lon != null)
                    navigationCoord.value = doubleArrayOf(lat, lon)
            } catch (error: Throwable) {
                _state.emit(ScreenState.Error("City not found!"))
            } finally {
                _state.emit(ScreenState.Loading(false))
            }
        }
    }

    sealed interface ScreenEvent {
        data class OnQueryChangeName(val query: String) : ScreenEvent
        data class OnQueryChangeCoord(val lat: Double?, val lon: Double?) : ScreenEvent
        data class OnCreate(val id: Int) : ScreenEvent
    }

    sealed interface ScreenState {
        object Initial : ScreenState
        data class Loading(val isLoading: Boolean) : ScreenState
        data class Error(val error: String) : ScreenState
        data class SuccessData(val weatherInfo: WeatherInfo) : ScreenState
    }

    data class ScreenStateData(
        val isLoading: Boolean = false,
        val error: String? = null,
        val weatherInfo: WeatherInfo? = null,
        val navigateToX: String? = null
    )

    sealed interface ScreenCommand {
        object Initial : ScreenCommand
        data class ShowToast(val text: String): ScreenCommand
    }

    companion object {
        fun factory(
            weatherByNameUseCase: GetWeatherByNameUseCase,
            weatherByCoordUseCase: GetWeatherByCoordUseCase,
            saveWeatherUseCase: SaveWeatherUseCase,
            deleteWeatherUseCase: DeleteWeatherUseCase,
            findWeatherByNameUseCase: FindWeatherByNameUseCase

        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                return SearchViewModel(
                    weatherByNameUseCase,
                    weatherByCoordUseCase,
                    saveWeatherUseCase,
                    deleteWeatherUseCase,
                    findWeatherByNameUseCase
                ) as T
            }
        }
    }
}

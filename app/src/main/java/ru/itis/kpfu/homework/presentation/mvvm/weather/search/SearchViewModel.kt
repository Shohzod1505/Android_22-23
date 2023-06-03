package ru.itis.kpfu.homework.presentation.mvvm.weather.search

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.launch
import ru.itis.kpfu.homework.data.weather.datasource.local.entity.Weather
import ru.itis.kpfu.homework.di.DataContainer
import ru.itis.kpfu.homework.domain.weather.*
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val getWeatherByNameUseCase: GetWeatherByNameUseCase,
    private val getWeatherByCoordUseCase: GetWeatherByCoordUseCase,
    private val saveWeatherUseCase: SaveWeatherUseCase,
    private val deleteWeatherUseCase: DeleteWeatherUseCase,
    private val findWeatherByNameUseCase: FindWeatherByNameUseCase,
//    private val resourceProvider: ResourceProvider,
): ViewModel() {

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    val navigationName = MutableLiveData<String?>(null)

    val navigationCoord = MutableLiveData<DoubleArray?>(null)

    fun loadWeather(query: String?) {
        viewModelScope.launch {
            try {
                _loading.value = true
                getWeatherByNameUseCase(query)
                navigationName.value = query
            } catch (error: Throwable) {
                _error.value = "City not found!"
            } finally {
                _loading.value = false
            }
        }
    }

    fun loadWeather(lat: Double?, lon: Double?) {
        viewModelScope.launch {
            try {
                _loading.value = true
                getWeatherByCoordUseCase(lat, lon)
                if (lat != null && lon != null)
                    navigationCoord.value = doubleArrayOf(lat, lon)
            } catch (error: Throwable) {
                _error.value = "City not found!"
            } finally {
                _loading.value = false
            }
        }
    }

    suspend fun saveWeather(weather: Weather) {
        saveWeatherUseCase(weather)
    }

    suspend fun deleteWeather(weather: Weather) {
        deleteWeatherUseCase(weather)
    }

    suspend fun findWeatherByName(name: String): Weather? = findWeatherByNameUseCase(name)

    suspend fun getWeatherByName(query: String?) = getWeatherByNameUseCase(query)

    suspend fun getWeatherByCoord(lat: Double?, lon: Double?) = getWeatherByCoordUseCase(lat, lon)

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
//                val resourceProvider = DataContainer.provideResources()
//                val savedStateHandle = extras.createSavedStateHandle()
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

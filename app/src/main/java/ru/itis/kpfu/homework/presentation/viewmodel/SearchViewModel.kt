package ru.itis.kpfu.homework.presentation.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.launch
import ru.itis.kpfu.homework.di.ResourceProvider
import ru.itis.kpfu.homework.di.DataContainer
import ru.itis.kpfu.homework.domain.weather.GetWeatherByCoordUseCase
import ru.itis.kpfu.homework.domain.weather.GetWeatherByNameUseCase

class SearchViewModel(
    private val getWeatherByNameUseCase: GetWeatherByNameUseCase,
    private val getWeatherByCoordUseCase: GetWeatherByCoordUseCase,
    private val savedState: SavedStateHandle,
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

    suspend fun getWeatherByName(query: String?) = getWeatherByNameUseCase(query)

    suspend fun getWeatherByCoord(lat: Double?, lon: Double?) = getWeatherByCoordUseCase(lat, lon)

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val weatherByNameUseCase = DataContainer.weatherByNameUseCase
                val weatherByCoordUseCase = DataContainer.weatherByCoordUseCase
//                val resourceProvider = DataContainer.provideResources()
                val savedStateHandle = extras.createSavedStateHandle()
                return SearchViewModel(
                    weatherByNameUseCase,
                    weatherByCoordUseCase,
                    savedStateHandle,
//                    resourceProvider
                ) as T
            }
        }

        val FactoryExt: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val weatherByNameUseCase = DataContainer.weatherByNameUseCase
                val weatherByCoordUseCase = DataContainer.weatherByCoordUseCase
//                val resourceProvider = DataContainer.provideResources()
                val savedStateHandle = createSavedStateHandle()
                SearchViewModel(
                    weatherByNameUseCase,
                    weatherByCoordUseCase,
                    savedStateHandle,
//                    resourceProvider
                )
            }
        }

    }
}

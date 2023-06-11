package ru.itis.kpfu.homework.presentation.mvvm.weather.detail

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
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

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    val navigation = MutableLiveData<Boolean>(null)

    var disposable: CompositeDisposable = CompositeDisposable()

    fun loadWeather(query: String?) {

        disposable += getWeatherByNameUseCase(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onSuccess = { weatherInfo ->
                _weatherInfo.value = weatherInfo
            }, onError = {
                _error.value = "City not found!"
            },)
    }

    fun loadWeather(lat: Double?, lon: Double?) {
        disposable += getWeatherByCoordUseCase(lat, lon)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onSuccess = { weatherInfo ->
                _weatherInfo.value = weatherInfo
            }, onError = {
                _error.value = "City not found!"
            },)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
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

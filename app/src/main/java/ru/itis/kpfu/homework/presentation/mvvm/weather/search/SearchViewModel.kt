package ru.itis.kpfu.homework.presentation.mvvm.weather.search

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import ru.itis.kpfu.homework.data.weather.datasource.local.entity.Weather
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

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    val navigationName = MutableLiveData<String?>(null)

    val navigationCoord = MutableLiveData<DoubleArray?>(null)

    var disposable: CompositeDisposable = CompositeDisposable()

    fun loadWeather(query: String?) {
        disposable += getWeatherByNameUseCase(query)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _loading.value = true }
            .doAfterTerminate { _loading.value = false }
            .subscribeBy(onSuccess = {
                navigationName.value = query
            }, onError = {
                _error.value = "City not found!"
            },)
    }

    fun loadWeather(lat: Double?, lon: Double?) {
        disposable += getWeatherByCoordUseCase(lat, lon)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _loading.value = true }
            .doAfterTerminate { _loading.value = false }
            .subscribeBy(onSuccess = {
                if (lat != null && lon != null)
                    navigationCoord.value = doubleArrayOf(lat, lon)
            }, onError = {
                _error.value = "City not found!"
            },)
    }

    fun saveWeather(weather: Weather): Completable {
        return saveWeatherUseCase(weather)
    }

    fun deleteWeather(weather: Weather): Completable {
        return deleteWeatherUseCase(weather)
    }

    fun getWeatherByName(query: String?) = getWeatherByNameUseCase(query)

    fun getWeatherByCoord(lat: Double?, lon: Double?) = getWeatherByCoordUseCase(lat, lon)

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
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

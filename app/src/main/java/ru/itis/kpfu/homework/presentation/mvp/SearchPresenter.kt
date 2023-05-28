package ru.itis.kpfu.homework.presentation.mvp

import kotlinx.coroutines.*
import ru.itis.kpfu.homework.domain.weather.GetWeatherByCoordUseCase
import ru.itis.kpfu.homework.domain.weather.GetWeatherByNameUseCase

class SearchPresenter(
    private val view: SearchView,
    private val getWeatherByNameUseCase: GetWeatherByNameUseCase,
    private val getWeatherByCoordUseCase: GetWeatherByCoordUseCase,
) {

    private val presenterScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    fun onClear() {
        presenterScope.cancel()
    }

    fun loadWeather(query: String?) {
        presenterScope.launch {
            try {
                view.showLoading(true)
                getWeatherByNameUseCase(query)
                view.navigate(query)
            } catch (error: Throwable) {
                view. showError()
            } finally {
                view. showLoading(false)
            }
        }
    }

    fun loadWeather(lat: Double?, lon: Double?) {
        presenterScope.launch {
            try {
                view.showLoading(true)
                getWeatherByCoordUseCase(lat, lon)
                view.navigate(lat, lon)
            } catch (error: Throwable) {
                view. showError()
            } finally {
                view.showLoading(false)
            }
        }
    }

    suspend fun getWeatherByName(query: String?) = getWeatherByNameUseCase(query)

    suspend fun getWeatherByCoord(lat: Double?, lon: Double?) = getWeatherByCoordUseCase(lat, lon)

}

package ru.itis.kpfu.homework.presentation.moxy

import kotlinx.coroutines.*
import moxy.MvpPresenter
import moxy.presenterScope
import ru.itis.kpfu.homework.domain.weather.GetWeatherByCoordUseCase
import ru.itis.kpfu.homework.domain.weather.GetWeatherByNameUseCase

class SearchPresenter(
    private val getWeatherByNameUseCase: GetWeatherByNameUseCase,
    private val getWeatherByCoordUseCase: GetWeatherByCoordUseCase,
) : MvpPresenter<SearchView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun loadWeather(query: String?) {
        presenterScope.launch {
            try {
                viewState.showLoading(true)
                getWeatherByNameUseCase(query)
                viewState.navigate(query)
            } catch (error: Throwable) {
                viewState. showError()
            } finally {
                viewState. showLoading(false)
            }
        }
    }

    fun loadWeather(lat: Double?, lon: Double?) {
        presenterScope.launch {
            try {
                viewState.showLoading(true)
                getWeatherByCoordUseCase(lat, lon)
                viewState.navigate(lat, lon)
            } catch (error: Throwable) {
                viewState. showError()
            } finally {
                viewState.showLoading(false)
            }
        }
    }

    suspend fun getWeatherByName(query: String?) = getWeatherByNameUseCase(query)

    suspend fun getWeatherByCoord(lat: Double?, lon: Double?) = getWeatherByCoordUseCase(lat, lon)

}

package ru.itis.kpfu.homework.presentation.screens;

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ru.itis.kpfu.homework.R
import ru.itis.kpfu.homework.data.weather.datasource.local.WeatherRepository
import ru.itis.kpfu.homework.data.weather.mapper.toWeather
import ru.itis.kpfu.homework.presentation.adapter.SpaceItemDecorator
import ru.itis.kpfu.homework.presentation.adapter.WeatherAdapter
import ru.itis.kpfu.homework.di.DataContainer
import ru.itis.kpfu.homework.databinding.FragmentSearchBinding
import ru.itis.kpfu.homework.domain.weather.GetWeatherByCoordUseCase
import ru.itis.kpfu.homework.domain.weather.GetWeatherByNameUseCase
import ru.itis.kpfu.homework.domain.weather.WeatherInfo

class SearchFragment : Fragment(R.layout.fragment_search) {
    private var binding: FragmentSearchBinding? = null
    private var adapter: WeatherAdapter? = null
    private var repositoryRoom: WeatherRepository? = null
    private val getWeatherByNameUseCase: GetWeatherByNameUseCase = DataContainer.weatherByNameUseCase
    private val getWeatherByCoordUseCase: GetWeatherByCoordUseCase = DataContainer.weatherByCoordUseCase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)
        setHasOptionsMenu(true)
        binding?.run {
            repositoryRoom = WeatherRepository(requireContext())
            val itemDecoration = SpaceItemDecorator(requireContext(), 16f)
            lifecycleScope.launch {
                val locationList: List<WeatherInfo> = async {
                    arrayListOf(
                        getWeatherByCoordUseCase(55.742740,49.1816907),
                        getWeatherByCoordUseCase(55.742740,48.1816907),
                        getWeatherByCoordUseCase(55.742740,47.1816907),
                        getWeatherByCoordUseCase(55.742740,46.1816907),
                        getWeatherByCoordUseCase(55.742740,45.1816907),
                        getWeatherByCoordUseCase(55.742740,44.1816907),
                        getWeatherByCoordUseCase(55.742740,43.1816907),
                        getWeatherByCoordUseCase(55.742740,42.1816907),
                        getWeatherByCoordUseCase(55.742740,41.1816907),
                        getWeatherByCoordUseCase(55.742740,40.1816907),
                    )
                }.await()
                adapter = WeatherAdapter(locationList) {
                    lifecycleScope.launch {
                        loadWeather(it.lat, it.lon)
                        repositoryRoom?.saveWeather(getWeatherByCoordUseCase(it.lat, it.lon).toWeather())
                    }
                }
                rvWeather.adapter = adapter
                rvWeather.addItemDecoration(itemDecoration)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when(item.itemId) {
            R.id.action_search -> {
                val searchView = item.actionView as SearchView
                searchView.queryHint = "Search"

                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        searchView.clearFocus()
                        searchView.setQuery("", false)
                        item.collapseActionView()
                        loadWeather(query)
                        lifecycleScope.launch {
                            repositoryRoom?.saveWeather(getWeatherByNameUseCase(query).toWeather())
                        }
                        return true
                    }
                    override fun onQueryTextChange(query: String?): Boolean {
                        return true
                    }
                })
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }

    private fun loadWeather(query: String?) {
        lifecycleScope.launch {
            try {
                showLoading(true)
                getWeatherByNameUseCase(query)
                navigate(query)
            } catch (error: Throwable) {
                showError()
            } finally {
                showLoading(false)
            }
        }
    }

    private fun loadWeather(lat: Double?, lon: Double?) {
        lifecycleScope.launch {
            try {
                showLoading(true)
                getWeatherByCoordUseCase(lat, lon)
                navigate(lat, lon)
            } catch (error: Throwable) {
                showError()
            } finally {
                showLoading(false)
            }
        }
    }

    private fun navigate(query: String?) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, DetailFragment.newInstanceQuery(query, true))
            .addToBackStack("SearchFragment")
            .commit()
    }

    private fun navigate(lat: Double?, lon: Double?) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, DetailFragment.newInstanceCoord(lat, lon, false))
            .addToBackStack("SearchFragment")
            .commit()
    }

    private fun showLoading(isShow: Boolean) {
        binding?.progress?.isVisible = isShow
    }

    private fun showError() {
        Toast.makeText(requireContext(), "City not found", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}

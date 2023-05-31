package ru.itis.kpfu.homework.presentation.mvvm.weather.search;

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView as KotlinSearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ru.itis.kpfu.homework.R
import ru.itis.kpfu.homework.data.weather.datasource.local.WeatherRepository
import ru.itis.kpfu.homework.data.weather.mapper.toWeather
import ru.itis.kpfu.homework.presentation.adapter.SpaceItemDecorator
import ru.itis.kpfu.homework.presentation.adapter.WeatherAdapter
import ru.itis.kpfu.homework.databinding.FragmentSearchBinding
import ru.itis.kpfu.homework.domain.weather.WeatherInfo
import ru.itis.kpfu.homework.presentation.mvvm.weather.detail.DetailFragment
import javax.inject.Inject

class SearchFragment : DaggerFragment(R.layout.fragment_search) {
    private var binding: FragmentSearchBinding? = null
    private var adapter: WeatherAdapter? = null
    private var repositoryRoom: WeatherRepository? = null

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: SearchViewModel by viewModels {
        factory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchBinding.bind(view)
        setHasOptionsMenu(true)
        observeViewModel()

        binding?.run {
            repositoryRoom = WeatherRepository(requireContext())
            val itemDecoration = SpaceItemDecorator(requireContext(), 16f)
            lifecycleScope.launch {
                val locationList: List<WeatherInfo?> = async {
                    arrayListOf(
                        viewModel.getWeatherByCoord(55.742740,49.1816907),
                        viewModel.getWeatherByCoord(55.742740,48.1816907),
                        viewModel.getWeatherByCoord(55.742740,47.1816907),
                        viewModel.getWeatherByCoord(55.742740,46.1816907),
                        viewModel.getWeatherByCoord(55.742740,45.1816907),
                        viewModel.getWeatherByCoord(55.742740,44.1816907),
                        viewModel.getWeatherByCoord(55.742740,43.1816907),
                        viewModel.getWeatherByCoord(55.742740,42.1816907),
                        viewModel.getWeatherByCoord(55.742740,41.1816907),
                        viewModel.getWeatherByCoord(55.742740,40.1816907),
                    )
                }.await()
                adapter = WeatherAdapter(locationList) {
                    lifecycleScope.launch {
                        viewModel.loadWeather(it?.lat, it?.lon)
                        viewModel.getWeatherByCoord(it?.lat, it?.lon).let {
                                repositoryRoom?.saveWeather(it.toWeather())
                            }
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
                val searchView = item.actionView as KotlinSearchView
                searchView.queryHint = "Search"

                searchView.setOnQueryTextListener(object : KotlinSearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        searchView.clearFocus()
                        searchView.setQuery("", false)
                        item.collapseActionView()
                        viewModel.loadWeather(query)
                        lifecycleScope.launch {
                            viewModel.getWeatherByName(query).let {
                                repositoryRoom?.saveWeather(it.toWeather())
                            }
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

    private fun observeViewModel() {
        with(viewModel) {
            loading.observe(viewLifecycleOwner) {
                binding?.progress?.isVisible = it
            }

            error.observe(viewLifecycleOwner) {
                showError(it)
            }

            navigationName.observe(viewLifecycleOwner) {
                if (it == null) return@observe
                navigate(it)
            }

            navigationCoord.observe(viewLifecycleOwner) {
                if (it == null) return@observe
                navigate(it[0], it[1])
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

    private fun showError(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}

package ru.itis.kpfu.homework.presentation.mvvm.weather.search;

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.Flowables
import io.reactivex.rxjava3.subjects.BehaviorSubject
import ru.itis.kpfu.homework.R
import ru.itis.kpfu.homework.data.weather.mapper.toWeather
import ru.itis.kpfu.homework.presentation.adapter.SpaceItemDecorator
import ru.itis.kpfu.homework.presentation.adapter.WeatherAdapter
import ru.itis.kpfu.homework.databinding.FragmentSearchBinding
import ru.itis.kpfu.homework.presentation.mvvm.weather.detail.DetailFragment
import javax.inject.Inject

class SearchFragment : DaggerFragment(R.layout.fragment_search) {
    private var binding: FragmentSearchBinding? = null
    private var adapter: WeatherAdapter? = null

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: SearchViewModel by viewModels {
        factory
    }
    private val searchQuerySubject = BehaviorSubject.createDefault("")
    private var searchDisposable: Disposable? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)
        setHasOptionsMenu(true)

        observeViewModel()
        binding?.run {
            val itemDecoration = SpaceItemDecorator(requireContext(), 16f)

            val locationList = listOf(
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

            val weatherListSingle = Observable.fromIterable(locationList)
                .flatMapSingle { it }

            val weatherList = weatherListSingle
                .map { it }
                .toList()
                .blockingGet()

                adapter = WeatherAdapter(weatherList) {
                viewModel.loadWeather(it?.lat, it?.lon)
                viewModel.getWeatherByCoord(it?.lat, it?.lon)
                    .flatMap { weather ->
                        viewModel.saveWeather(weather.toWeather())
                            .andThen(Single.just(weather))
                    }
                }
//            val x = requireActivity().findViewById<SearchView>(R.id.action_search)

            rvWeather.adapter = adapter
            rvWeather.addItemDecoration(itemDecoration)
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
                        viewModel.loadWeather(query)
                        viewModel.getWeatherByName(query)
                            .flatMap { weather ->
                                viewModel.saveWeather(weather.toWeather())
                                    .andThen(Single.just(weather))
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

    private fun SearchView.observeQuery() =
        Flowable.create<String>({ emitter ->
            this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    emitter.onNext(query)
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    emitter.onNext(newText)
                    return true
                }
            })
        }, BackpressureStrategy.LATEST)


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}

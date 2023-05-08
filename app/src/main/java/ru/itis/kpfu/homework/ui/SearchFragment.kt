package ru.itis.kpfu.homework.ui;

import android.Manifest
import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.pm.PackageManager
import android.database.Cursor
import android.database.MatrixCursor
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.cursoradapter.widget.CursorAdapter
import androidx.cursoradapter.widget.SimpleCursorAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ru.itis.kpfu.homework.R
import ru.itis.kpfu.homework.adapter.SpaceItemDecorator
import ru.itis.kpfu.homework.adapter.WeatherAdapter
import ru.itis.kpfu.homework.data.DataContainer
import ru.itis.kpfu.homework.data.response.WeatherResponse
import ru.itis.kpfu.homework.databinding.FragmentSearchBinding

class SearchFragment : Fragment(R.layout.fragment_search) {
    private var binding: FragmentSearchBinding? = null
    private var adapter: WeatherAdapter? = null
    private val api = DataContainer.weatherApi
//    private var mFusedLocationClient: FusedLocationProviderClient? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)
        setHasOptionsMenu(true)
        binding?.run {
//            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
//            getLastLocation()

            val itemDecoration = SpaceItemDecorator(requireContext(), 16f)
            lifecycleScope.launch {
                val locationList: List<WeatherResponse> = async {
                    arrayListOf(
                        api.getWeather(55.742740,49.1816907),
                        api.getWeather(55.742740,48.1816907),
                        api.getWeather(55.742740,47.1816907),
                        api.getWeather(55.742740,46.1816907),
                        api.getWeather(55.742740,45.1816907),
                        api.getWeather(55.742740,44.1816907),
                        api.getWeather(55.742740,43.1816907),
                        api.getWeather(55.742740,42.1816907),
                        api.getWeather(55.742740,41.1816907),
                        api.getWeather(55.742740,40.1816907),
                    )
                }.await()
                adapter = WeatherAdapter(locationList) {
                    loadWeather(it.coord?.lat, it.coord?.lon)
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
                val from = arrayOf(SearchManager.SUGGEST_COLUMN_TEXT_1)
                val to = intArrayOf(R.id.item_label)
                val cursorAdapter = SimpleCursorAdapter(
                    context,
                    R.layout.search_item,
                    null,
                    from,
                    to,
                    CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
                )
                val suggestions = listOf("Kazan", "Moscow", "London", "Kair", "Kaliningrad")

                val searchView = item.actionView as SearchView
                searchView.suggestionsAdapter = cursorAdapter

                searchView.queryHint = "Search"
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        searchView.clearFocus()
                        searchView.setQuery("", false)
                        item.collapseActionView()
                        loadWeather(query)
                        return true
                    }
                    override fun onQueryTextChange(query: String?): Boolean {
                        val cursor = MatrixCursor(arrayOf(
                            BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1
                        ))
                        query?.let {
                            suggestions.forEachIndexed { index, suggestion ->
                                if (suggestion.contains(query, true))
                                    cursor.addRow(arrayOf(index, suggestion))
                            }
                        }

                        cursorAdapter.changeCursor(cursor)
                        return true
                    }
                })
                searchView.setOnSuggestionListener(object : SearchView.OnSuggestionListener {
                    override fun onSuggestionSelect(position: Int): Boolean {
                        return false
                    }
                    @SuppressLint("Range")
                    override fun onSuggestionClick(position: Int): Boolean {
                        val cursor = searchView.suggestionsAdapter.getItem(position) as Cursor
                        val selection = cursor.getString(cursor.getColumnIndex(
                            SearchManager.SUGGEST_COLUMN_TEXT_1
                        ))
                        searchView.setQuery(selection, false)
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
                api.getWeather(query)
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
                api.getWeather(lat, lon)
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

//    private fun getLastLocation(){
//        if (ActivityCompat.checkSelfPermission(
//                requireContext(),
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//            && ActivityCompat.checkSelfPermission(
//                requireContext(),
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(
//                requireActivity(),
//                arrayOf(
//                    Manifest.permission.ACCESS_FINE_LOCATION,
//                    Manifest.permission.ACCESS_COARSE_LOCATION
//                ),
//                101
//            )
//        }
//
//        mFusedLocationClient?.lastLocation?.addOnSuccessListener { location ->
//            if (location != null) {
//                val latitude = location.latitude
//                val longitude = location.longitude
//                Log.d("My", "Latitude $latitude")
//                Log.d("My", "Longitude $longitude")
//            }
//        }?.addOnFailureListener {
//            Log.d("My", "Location not found")
//        }
//    }

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

package ru.itis.kpfu.homework.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.itis.kpfu.homework.databinding.ItemWeatherBinding
import ru.itis.kpfu.homework.domain.weather.WeatherInfo

class WeatherAdapter(
    private val list: List<WeatherInfo>,
    private val action: (WeatherInfo) -> Unit,
) : RecyclerView.Adapter<WeatherHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherHolder = WeatherHolder(
        binding = ItemWeatherBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        action = action
    )

    override fun onBindViewHolder(
        holder: WeatherHolder,
        position: Int
    ) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

}

package ru.itis.kpfu.homework.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.itis.kpfu.homework.data.response.WeatherResponse
import ru.itis.kpfu.homework.databinding.ItemWeatherBinding

class WeatherAdapter(
    private val list: List<WeatherResponse>,
) : RecyclerView.Adapter<WeatherHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherHolder = WeatherHolder(
        binding = ItemWeatherBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: WeatherHolder,
        position: Int
    ) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

}

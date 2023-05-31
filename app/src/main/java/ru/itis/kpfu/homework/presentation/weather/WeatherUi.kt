package ru.itis.kpfu.homework.presentation.weather

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import coil.load
import ru.itis.kpfu.homework.R

class WeatherUi {

    fun showWeatherIcon(image: ImageView?, id: String?) {
        image?.load("https://openweathermap.org/img/w/$id.png")
    }

    fun showHumidityIcon(image: ImageView?, humidity: Int) {
        image?.run {
            when(humidity) {
                in 0..35 -> load(R.drawable.icon_dry)
                in 36..60 -> load(R.drawable.baseline_emoji_emotions_24)
                in 61..100 -> load(R.drawable.icon_humidity)
                else -> {}
            }
        }
    }

    fun showWindDirectionIcon(image: ImageView?, degree: Int) {
        image?.run {
            when(degree) {
                in 0..22 -> load(R.drawable.baseline_north_24)
                in 22..67 -> load(R.drawable.baseline_north_east_24)
                in 67..112 -> load(R.drawable.baseline_east_24)
                in 112..157 -> load(R.drawable.baseline_south_east_24)
                in 157..202 -> load(R.drawable.baseline_south_24)
                in 202..247 -> load(R.drawable.baseline_south_west_24)
                in 247..292 -> load(R.drawable.baseline_west_24)
                in 292..337 -> load(R.drawable.baseline_north_west_24)
                in 337..360 -> load(R.drawable.baseline_north_24)
                else -> {}
            }
        }
    }

    fun showTemp(view: TextView?, temp: Double?) {
        view?.run {
            text = "$temp C"
            if(temp != null)
            when(temp) {
                in -40.0..-5.0 -> setTextColor(Color.BLUE)
                in -5.0..15.0 -> setTextColor(Color.CYAN)
                in 15.0..25.0 -> setTextColor(Color.GREEN)
                in 25.0..35.0 -> setTextColor(Color.YELLOW)
                in 35.0..50.0 -> setTextColor(Color.RED)
            }
        }
    }

    fun showHumidity(view: TextView?, humidity: Int) {
        view?.run {
            text = humidity.toString()
        }
    }

    fun showWindDirection(view: TextView?, degree: Int) {
        view?.run {
            text = when(degree) {
                in 0..22 -> "North"
                in 22..67 -> "Northeast"
                in 67..112 -> "East"
                in 112..157 -> "SouthEast"
                in 157..202 -> "South"
                in 202..247 -> "SouthWest"
                in 247..292 -> "West"
                in 292..337 -> "Northwest"
                in 337..360 -> "North"
                else -> {""}
            }
        }
    }

}

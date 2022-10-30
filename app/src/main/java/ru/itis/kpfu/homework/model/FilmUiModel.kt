package ru.itis.kpfu.homework.model

sealed class MainItem(val id: Int) {
    data class Ads(val ads: String): MainItem(0)

    data class FilmUiModel(
        val title: String,
        val year: Int,
        val genre: String,
        val rate: Double,
        val cover: String,
    ): MainItem(0)
}



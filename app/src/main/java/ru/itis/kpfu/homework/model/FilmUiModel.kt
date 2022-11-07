package ru.itis.kpfu.homework.model

sealed interface MainItem {
    data class Ads(val ads: String): MainItem

    data class FilmUiModel(
        val id: Int,
        val title: String,
        val year: Int,
        val genre: String,
        val rate: Double,
        val cover: String,
    ): MainItem
}



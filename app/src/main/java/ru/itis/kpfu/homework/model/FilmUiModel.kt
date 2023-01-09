package ru.itis.kpfu.homework.model

sealed interface MainItem {
    val id: Int

    data class Ads(val ads: String, override val id: Int): MainItem

    data class FilmUiModel(
        override val id: Int,
        val title: String,
        val year: Int,
        val genre: String,
        val rate: Double,
        val cover: String,
    ): MainItem
}



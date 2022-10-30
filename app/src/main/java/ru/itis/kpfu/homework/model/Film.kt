package ru.itis.kpfu.homework.model

data class Film(
    val id: Int,
    val title: String,
    val year: Int,
    val genre: String,
    val rate: Double,
    val cover: String,
)

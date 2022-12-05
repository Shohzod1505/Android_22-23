package ru.itis.kpfu.homework.model

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes

data class Song(
    val id: Int,
    val audioTitle: String,
    val author: String,
    @DrawableRes val cover: Int,
    @RawRes val audio: Int,

)

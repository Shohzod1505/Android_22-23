package ru.itis.kpfu.homework.service

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class MusicAction(val key: Int) : Parcelable {
    PLAY(0), PAUSE(1), STOP(2), PREV(3), NEXT(4)
}
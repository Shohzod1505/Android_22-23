package ru.itis.kpfu.homework

import androidx.fragment.app.Fragment

fun Fragment.ran() {

}

infix fun Int.gigaSum(value: Int): Int = this + value * 10

fun main() {
    val result = 5.gigaSum(10)
}
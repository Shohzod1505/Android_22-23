package ru.itis.kpfu.homework.presentation.mvp

interface SearchView {

    fun navigate(query: String?)

    fun navigate(lat: Double?, lon: Double?)

    fun showLoading(isShow: Boolean)

    fun showError()

}

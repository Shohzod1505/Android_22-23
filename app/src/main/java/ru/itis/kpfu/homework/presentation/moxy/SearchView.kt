package ru.itis.kpfu.homework.presentation.moxy

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution
import moxy.viewstate.strategy.alias.Skip

@AddToEndSingle
interface SearchView : MvpView {

    @OneExecution
    fun navigate(query: String?)

    @OneExecution
    fun navigate(lat: Double?, lon: Double?)

    fun showLoading(isShow: Boolean)

    @Skip
    fun showError()

}

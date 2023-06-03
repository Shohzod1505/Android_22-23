package ru.itis.kpfu.homework.presentation.mvvm

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.itis.kpfu.homework.di.FeatureScope
import ru.itis.kpfu.homework.presentation.mvvm.weather.detail.DetailFragment
import ru.itis.kpfu.homework.presentation.mvvm.weather.detail.DetailModule
import ru.itis.kpfu.homework.presentation.mvvm.weather.search.SearchFragment
import ru.itis.kpfu.homework.presentation.mvvm.weather.search.SearchModule

@Module
interface FeatureModule {

    @FeatureScope
    @ContributesAndroidInjector(modules = [SearchModule::class])
    fun contributeSearchFragment(): SearchFragment

    @FeatureScope
    @ContributesAndroidInjector(modules = [DetailModule::class])
    fun contributeDetailFragment(): DetailFragment

}

package ru.itis.kpfu.homework.presentation.mvvm.weather.search

import dagger.Subcomponent
import ru.itis.kpfu.homework.di.FeatureScope

@FeatureScope
@Subcomponent(modules = [SearchModule::class])
interface SearchComponent {

    fun inject(searchFragment: SearchFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): SearchComponent
    }

}

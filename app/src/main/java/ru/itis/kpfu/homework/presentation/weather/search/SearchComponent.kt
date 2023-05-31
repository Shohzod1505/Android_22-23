package ru.itis.kpfu.homework.presentation.weather.search

import dagger.Subcomponent
import ru.itis.kpfu.homework.di.FeatureScope

@FeatureScope
@Subcomponent(modules = [SearchModule::class])
interface SearchComponent {

    fun inject(searchFragment: SearchFragment)

    @Subcomponent.Builder
    interface Builder {

        fun provideModule(module: SearchModule): Builder
        fun build(): SearchComponent
    }

}

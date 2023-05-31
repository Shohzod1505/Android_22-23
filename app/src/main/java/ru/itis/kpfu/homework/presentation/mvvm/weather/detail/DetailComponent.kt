package ru.itis.kpfu.homework.presentation.mvvm.weather.detail

import dagger.Subcomponent
import ru.itis.kpfu.homework.di.FeatureScope

@FeatureScope
@Subcomponent(modules = [DetailModule::class])
interface DetailComponent {

    fun inject(detailFragment: DetailFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): DetailComponent
    }

}

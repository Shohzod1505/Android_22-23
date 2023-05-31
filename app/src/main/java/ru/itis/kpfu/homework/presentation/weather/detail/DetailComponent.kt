package ru.itis.kpfu.homework.presentation.weather.detail

import dagger.BindsInstance
import dagger.Subcomponent
import ru.itis.kpfu.homework.di.FeatureScope
import ru.itis.kpfu.homework.presentation.weather.search.SearchFragment
import javax.inject.Named

@FeatureScope
@Subcomponent(modules = [DetailModule::class])
interface DetailComponent {

    fun inject(detailFragment: DetailFragment)

    @Subcomponent.Builder
    interface Builder {

        fun provideModule(module: DetailModule): Builder
        fun build(): DetailComponent
    }

}

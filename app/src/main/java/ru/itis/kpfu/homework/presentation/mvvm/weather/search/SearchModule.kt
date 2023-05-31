package ru.itis.kpfu.homework.presentation.mvvm.weather.search

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.itis.kpfu.homework.di.FeatureScope
import ru.itis.kpfu.homework.di.ViewModelKey

@Module
interface SearchModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    @FeatureScope
    fun provideViewModel(viewModel: SearchViewModel): ViewModel
}

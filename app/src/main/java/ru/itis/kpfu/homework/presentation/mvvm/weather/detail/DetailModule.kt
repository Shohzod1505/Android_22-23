package ru.itis.kpfu.homework.presentation.mvvm.weather.detail

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.itis.kpfu.homework.di.FeatureScope
import ru.itis.kpfu.homework.di.ViewModelKey

@Module
interface DetailModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    @FeatureScope
    fun provideViewModel(viewModel: DetailViewModel): ViewModel
}

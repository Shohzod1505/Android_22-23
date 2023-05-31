package ru.itis.kpfu.homework.presentation.mvvm

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import ru.itis.kpfu.homework.utils.AppViewModelFactory

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(
        factory: AppViewModelFactory
    ): ViewModelProvider.Factory
}

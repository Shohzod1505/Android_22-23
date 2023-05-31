package ru.itis.kpfu.homework.presentation.mvvm.auth

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import ru.itis.kpfu.homework.di.FeatureScope
import ru.itis.kpfu.homework.di.ViewModelKey
import ru.itis.kpfu.homework.domain.auth.LoginUseCase
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ArgCityId


@Module
interface AuthModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    @FeatureScope
    fun provideViewModel(viewModel: AuthViewModel): ViewModel
}

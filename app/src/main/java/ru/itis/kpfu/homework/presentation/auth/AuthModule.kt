package ru.itis.kpfu.homework.presentation.auth

import dagger.Module
import dagger.Provides
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ArgCityId


@Module
class AuthModule {

    @Provides
    fun provideViewModel(
        @ArgCityId id: Int
    ): AuthViewModel = AuthViewModel(id)


}

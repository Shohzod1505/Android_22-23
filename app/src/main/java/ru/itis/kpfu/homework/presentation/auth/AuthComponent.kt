package ru.itis.kpfu.homework.presentation.auth

import dagger.BindsInstance
import dagger.Subcomponent
import ru.itis.kpfu.homework.di.FeatureScope
import ru.itis.kpfu.homework.presentation.weather.detail.DetailComponent

@FeatureScope
@Subcomponent(modules = [AuthModule::class])
interface AuthComponent {

    fun inject(authFragment: AuthFragment)

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun setCityId(@ArgCityId id: Int): Builder
        fun provideModule(module: AuthModule): Builder
        fun build(): AuthComponent
    }

}

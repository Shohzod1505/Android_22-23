package ru.itis.kpfu.homework.presentation.mvvm.auth

import dagger.BindsInstance
import dagger.Subcomponent
import ru.itis.kpfu.homework.di.FeatureScope

@FeatureScope
@Subcomponent(modules = [AuthModule::class])
interface AuthComponent {

    fun inject(authFragment: AuthFragment)

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun setCityId(@ArgCityId id: Int): Builder
        fun build(): AuthComponent
    }

}

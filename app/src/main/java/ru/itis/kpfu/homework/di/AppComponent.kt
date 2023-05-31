package ru.itis.kpfu.homework.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.itis.kpfu.homework.presentation.MainActivity
import ru.itis.kpfu.homework.presentation.auth.AuthComponent
import ru.itis.kpfu.homework.presentation.weather.search.SearchFragment
import ru.itis.kpfu.homework.presentation.weather.detail.DetailComponent
import ru.itis.kpfu.homework.presentation.weather.search.SearchComponent
import ru.itis.kpfu.homework.utils.ResourceProvider
import javax.inject.Singleton

@Component(modules = [AppModule::class, NetWorkModule::class, WeatherModule::class])
@Singleton
interface AppComponent {

    fun provideContext(): Context

    fun provideResourceProvider(): ResourceProvider

    fun plusSearchComponent(): SearchComponent.Builder

    fun plusDetailComponent(): DetailComponent.Builder

    fun plusAuthComponent(): AuthComponent.Builder

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(applicationContext: Context): Builder

        fun build(): AppComponent
    }

}

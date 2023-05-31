package ru.itis.kpfu.homework.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import ru.itis.kpfu.homework.App
import ru.itis.kpfu.homework.presentation.mvvm.FeatureModule
import ru.itis.kpfu.homework.presentation.mvvm.ViewModelModule
import ru.itis.kpfu.homework.utils.ResourceProvider
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        NetWorkModule::class,
        WeatherModule::class,
        ViewModelModule::class,
        FeatureModule::class,
    ]
)
@Singleton
interface AppComponent {

    fun provideContext(): Context

    fun provideResourceProvider(): ResourceProvider

//    fun plusSearchComponent(): SearchComponent.Builder

//    fun plusDetailComponent(): DetailComponent.Builder

//    fun plusAuthComponent(): AuthComponent.Builder

    fun inject(application: App)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(applicationContext: Context): Builder

        fun build(): AppComponent
    }

}

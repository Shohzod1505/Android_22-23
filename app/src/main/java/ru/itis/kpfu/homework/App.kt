package ru.itis.kpfu.homework

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import ru.itis.kpfu.homework.di.*
import javax.inject.Inject

class App: Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .context(applicationContext)
            .build().apply {
                inject(this@App)
            }
    }

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

}

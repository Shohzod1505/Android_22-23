package ru.itis.kpfu.homework

import android.app.Application
import ru.itis.kpfu.homework.di.*

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .context(applicationContext)
            .build()

    }

    companion object {

        lateinit var appComponent: AppComponent
    }

}

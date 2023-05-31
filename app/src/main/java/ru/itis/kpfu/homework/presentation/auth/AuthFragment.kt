package ru.itis.kpfu.homework.presentation.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import ru.itis.kpfu.homework.App

class AuthFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.plusAuthComponent()
            .provideModule(AuthModule())
            .setCityId(3)
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
    }

}

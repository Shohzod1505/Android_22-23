package ru.itis.kpfu.homework.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.itis.kpfu.homework.R
import ru.itis.kpfu.homework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        binding?.run {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SearchFragment())
                .commit()
        }

    }
}

package ru.itis.kpfu.homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.itis.kpfu.homework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        binding?.run {

        }

    }
}

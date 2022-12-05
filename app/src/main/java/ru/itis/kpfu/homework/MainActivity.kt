package ru.itis.kpfu.homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.itis.kpfu.homework.databinding.ActivityMainBinding
import ru.itis.kpfu.homework.screen.MusicFragment

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        binding?.run {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MusicFragment())
                .commit()
        }
    }

}

//        startForegroundService(Intent(this@MainActivity, MusicService::class.java).apply {
//            putExtra("MUSIC_ACTION", MusicAction.PLAY as Parcelable)
//        })
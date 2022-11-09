package ru.itis.kpfu.homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.itis.kpfu.homework.databinding.ActivityAlarmBinding
import ru.itis.kpfu.homework.databinding.ActivityMainBinding

class AlarmActivity : AppCompatActivity() {
    private var binding: ActivityAlarmBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlarmBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
    }
}
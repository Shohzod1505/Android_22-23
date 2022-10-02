package ru.itis.kpfu.homework

import android.content.Intent
import android.content.Intent.EXTRA_TEXT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.itis.kpfu.homework.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private var _binding: ActivitySecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intentFromOtherApp = intent
        val text: String? = intentFromOtherApp.extras?.getString(EXTRA_TEXT)
        binding.tvTextFromAnotherApp.text = text

        binding.btGotoMainActivity.setOnClickListener {
                val intent = Intent(this@SecondActivity, MainActivity::class.java)
                startActivity(intent)
            }

    }

}
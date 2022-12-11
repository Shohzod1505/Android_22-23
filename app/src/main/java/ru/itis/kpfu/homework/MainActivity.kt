package ru.itis.kpfu.homework

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract.Events
import android.provider.CalendarContract
import ru.itis.kpfu.homework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btImplicit1.setOnClickListener {
                mapImplicitIntent("47.6,-122.3?z=11")
            }
            btImplicit2.setOnClickListener {
                calendarImplicitIntent("Graduation", "ITIS", 1664000000000, 1665000000000)
            }
            btImplicit3.setOnClickListener {
                phoneImplicitIntent("+77777777777")
            }
            btGotoSecondActivity.setOnClickListener {
                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                startActivity(intent)
            }
        }

    }

    private fun mapImplicitIntent(geoLocation: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("geo:$geoLocation")
        }

        val chooserIntent = Intent.createChooser(intent, "Map")

        if (chooserIntent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun calendarImplicitIntent(title: String, location: String, begin: Long, end: Long) {
        val intent = Intent(Intent.ACTION_INSERT).apply {
            data = Events.CONTENT_URI
            putExtra(Events.TITLE, title)
            putExtra(Events.EVENT_LOCATION, location)
            putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin)
            putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end)
        }

        val chooserIntent = Intent.createChooser(intent, "Calendar")

        if (chooserIntent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun phoneImplicitIntent(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }

        val chooserIntent = Intent.createChooser(intent, "Phone")

        if (chooserIntent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

}
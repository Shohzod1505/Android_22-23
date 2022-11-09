package ru.itis.kpfu.homework

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import ru.itis.kpfu.homework.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    private var alarmManager: AlarmManager? = null
    private var pendingIntent: PendingIntent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        binding?.run {
            val calender = Calendar.getInstance()

            val pickerDialogDate = DatePickerDialog.OnDateSetListener { datePicker, year, month, dayOfMonth ->
                calender.set(Calendar.YEAR, year)
                calender.set(Calendar.MONTH, month)
                calender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateLabel(tvDate, calender)
            }

            val pickerDialogTime = TimePickerDialog.OnTimeSetListener { timePicker, hourOfDay, minute ->
                tvTime.text = "$hourOfDay:$minute"
            }

            tvDate.setOnClickListener {
                DatePickerDialog(
                    this@MainActivity,
                    pickerDialogDate,
                    calender.get(Calendar.YEAR),
                    calender.get(Calendar.MONTH),
                    calender.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

            tvTime.setOnClickListener {
                val currentTime = Calendar.getInstance()
                val startHour = currentTime.get(Calendar.HOUR_OF_DAY)
                val startMinute = currentTime.get(Calendar.MINUTE)

                TimePickerDialog(
                    this@MainActivity,
                    pickerDialogTime,
                    startHour,
                    startMinute,
                    false
                ).show()
            }

            btStartAlarm.setOnClickListener {
                setAlarm(calender)
                tvAlarmTime.text = tvTime.text
            }
            btStopAlarm.setOnClickListener {
                cancelAlarm()
            }

        }

    }

    private fun setAlarm(calendar: Calendar) {

        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)

        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

        alarmManager?.apply {
            setRepeating(
                AlarmManager.RTC,
                calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
        }

        Toast.makeText(this, "Successful set", Toast.LENGTH_SHORT).show()
    }

    private fun cancelAlarm() {

        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)

        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

        alarmManager?.apply {
            cancel(pendingIntent)
        }

        Toast.makeText(this, "Successful canceled", Toast.LENGTH_SHORT).show()
    }


    private fun updateLabel(view: TextView, calendar: Calendar) {
        val format = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(format, Locale.ENGLISH)
        view.text = sdf.format(calendar.time)
    }

}
package ru.itis.kpfu.homework

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmReceiver : BroadcastReceiver() {

    private var notificationProvider: NotificationProvider? = null

    override fun onReceive(context: Context, p1: Intent?) {
        notificationProvider = NotificationProvider(context)
        notificationProvider?.showNotification(
            title = "Clock",
            text = "Alarm! Alarm! Alarm!"
        )
    }

}
package ru.itis.kpfu.homework.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import ru.itis.kpfu.homework.R
import ru.itis.kpfu.homework.model.Song
import ru.itis.kpfu.homework.screen.MusicFragment
import ru.itis.kpfu.homework.service.MusicService

class NotificationProvider(private val context: Context) {

    private val playSong = Intent(context, MusicService::class.java).apply {
        action = "PLAY"
    }.let {
        PendingIntent.getService(
            context,
            1,
            it,
            0
        )
    }

    private val prevSong = Intent(context, MusicService::class.java).apply {
        action = "PREV"
    }.let {
        PendingIntent.getService(
            context,
            2,
            it,
            0
        )
    }

    private val nextSong = Intent(context, MusicService::class.java).apply {
        action = "NEXT"
    }.let {
        PendingIntent.getService(
            context,
            3,
            it,
            0
        )
    }

    fun showNotification(song: Song) {
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val name = context.getString(R.string.channel_name)
        val descriptionText = context.getString(R.string.channel_description)

        val intent = Intent(context, MusicFragment::class.java)
        val pending = PendingIntent.getActivity(
            context,
            100,
            intent,
            PendingIntent.FLAG_ONE_SHOT,

            )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
            "CHANNEL_ID",
                name,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = descriptionText
            }.also {
                notificationManager.createNotificationChannel(it)
            }
        }

        val cover = BitmapFactory.decodeResource(context.resources, song.cover)


        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(
                context,
                "CHANNEL_ID"
            )
                .setSmallIcon(R.drawable.ic_baseline_pause_24)
                .addAction(R.drawable.ic_baseline_skip_previous_24, "Previous", prevSong)
                .addAction(R.drawable.ic_baseline_pause_24, "Play", playSong)
                .addAction(R.drawable.ic_baseline_skip_next_24, "Next", nextSong)
                .setContentTitle(song.audioTitle)
                .setLargeIcon(cover)
                .setSilent(true)

        notificationManager.notify(1234, builder.build())

    }

}
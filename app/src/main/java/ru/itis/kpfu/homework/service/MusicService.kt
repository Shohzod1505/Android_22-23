package ru.itis.kpfu.homework.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.widget.SeekBar
import androidx.annotation.RawRes
import java.lang.Exception

class MusicService : Service() {

    private var mediaPlayer = MediaPlayer()

    inner class MusicBinder : Binder() {

        fun startMusic(@RawRes audio: Int) {
            playLocaleMusic(audio)
        }

        fun playAndPauseMusic() {
            pause()
        }

        fun seekBar(seekBar: SeekBar) {
            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if(fromUser) mediaPlayer.seekTo(progress)
                }
                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }
                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }
            })
        }

        fun initSeekBar(seekBar: SeekBar) {
            seekBar.max = mediaPlayer.duration
            val handler = Handler()
            handler.postDelayed(object : Runnable {
                override fun run() {
                    try {
                        seekBar.progress = mediaPlayer.currentPosition
                        handler.postDelayed(this, 1000)
                    } catch (e: Exception) {
                        seekBar.progress = 0
                    }
                }
            }, 0)
        }
    }

//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        intent?.getParcelableExtra<MusicAction>("MUSIC_ACTION")?.let {
//            when(it) {
//                MusicAction.PLAY -> play()
//                MusicAction.PAUSE -> pause()
//                MusicAction.STOP -> stop()
//                MusicAction.PREV -> TODO()
//                MusicAction.NEXT -> TODO()
//            }
//        }
//        return START_STICKY
//    }

    override fun onBind(intent: Intent?): IBinder? = MusicBinder()

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    private fun pause() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        } else {
            mediaPlayer.start()
        }
    }

    private fun stop() {
        mediaPlayer.stop()
    }

    private fun playLocaleMusic(@RawRes audio: Int) {
        if (mediaPlayer.isPlaying) mediaPlayer.stop()
        mediaPlayer = MediaPlayer.create(applicationContext, audio)
        mediaPlayer.run {
            start()
            setOnCompletionListener {
                stop()
            }
        }
    }

}
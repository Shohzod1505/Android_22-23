package ru.itis.kpfu.homework.screen;

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.media.MediaPlayer
import android.os.Bundle
import android.os.IBinder
import android.os.Parcelable
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import ru.itis.kpfu.homework.R
import ru.itis.kpfu.homework.databinding.FragmentInfoBinding
import ru.itis.kpfu.homework.model.SongRepository
import ru.itis.kpfu.homework.service.MusicAction
import ru.itis.kpfu.homework.service.MusicService

class InfoFragment : Fragment(R.layout.fragment_info) {
    private var binding: FragmentInfoBinding? = null
    private var binder: MusicService.MusicBinder? = null

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            binder = service as? MusicService.MusicBinder
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            binder = null
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInfoBinding.bind(view)

        var isPlaying = true

        binding?.run {
            var currentId = arguments?.getInt(ARG_ID)
            currentId?.let { currentSong(it, tvInfoTitle, tvInfoAuthor, ivInfoCover, seekBar) }

            btPlayAndPause.setOnClickListener {
                isPlaying = if (isPlaying) {
                    btPlayAndPause.setImageResource(R.drawable.ic_baseline_play_arrow_24)
                    false
                } else {
                    btPlayAndPause.setImageResource(R.drawable.ic_baseline_pause_24)
                    true
                }
                binder?.playAndPauseMusic()
            }
            btPrev.setOnClickListener {
                currentId = if (currentId == 0) {
                    SongRepository.songList.size - 1
                } else {
                    currentId?.minus(1)
                }
                currentId?.let {currentSong(it, tvInfoTitle, tvInfoAuthor, ivInfoCover, seekBar) }
            }
            btNext.setOnClickListener {
                currentId = if (currentId == SongRepository.songList.size - 1) {
                    0
                } else {
                    currentId?.plus(1)
                }
                currentId?.let {currentSong(it, tvInfoTitle, tvInfoAuthor, ivInfoCover, seekBar) }
            }

        }

    }

    private fun currentSong (id: Int, titleView: TextView, authorView: TextView, image: ImageView, seekBar: SeekBar) {
        with(SongRepository.songList[id]) {
            binder?.initSeekBar(seekBar)
            binder?.startMusic(audio)
            binder?.seekBar(seekBar)
            titleView.text = audioTitle
            authorView.text = author
            Glide
                .with(requireContext())
                .load(cover)
                .into(image)
        }
    }

    override fun onStart() {
        super.onStart()
        activity?.bindService(
            Intent(requireContext(), MusicService::class.java).apply {
                putExtra("MUSIC_ACTION", MusicAction.PLAY as Parcelable)
            },
            connection,
            Service.BIND_AUTO_CREATE
        )
    }

    companion object {

        private const val ARG_ID = "ARG_ID"

        fun newInstance(id: Int) = InfoFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_ID, id)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
package ru.itis.kpfu.homework.screen;

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import ru.itis.kpfu.homework.R
import ru.itis.kpfu.homework.adapter.SongAdapter
import ru.itis.kpfu.homework.adapter.SpaceItemDecorator
import ru.itis.kpfu.homework.databinding.FragmentMusicBinding
import ru.itis.kpfu.homework.model.SongRepository
import ru.itis.kpfu.homework.service.MusicAction
import ru.itis.kpfu.homework.service.MusicService

class MusicFragment : Fragment(R.layout.fragment_music) {
    private var binding: FragmentMusicBinding? = null
    private var adapter: SongAdapter? = null
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
        binding = FragmentMusicBinding.bind(view)

        binding?.run {
            val itemDecoration = SpaceItemDecorator(requireContext(), 16f)
            adapter = SongAdapter(
                list = SongRepository.songList,
                glide = Glide.with(this@MusicFragment)
            ) {

                binder?.startMusic(it.audio)

                parentFragmentManager.beginTransaction()
                    .setCustomAnimations(
                        android.R.anim.slide_in_left,
                        android.R.anim.slide_out_right,
                        android.R.anim.slide_in_left,
                        android.R.anim.slide_out_right,
                    )
                    .replace(R.id.fragment_container, InfoFragment.newInstance(it.id))
                    .addToBackStack("MusicFragment")
                    .commit()
            }
            rvFilm.adapter = adapter
            rvFilm.addItemDecoration(itemDecoration)
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

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
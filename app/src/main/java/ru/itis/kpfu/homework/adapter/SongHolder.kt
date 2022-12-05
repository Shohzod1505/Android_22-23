package ru.itis.kpfu.homework.adapter

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Priority
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import ru.itis.kpfu.homework.R
import ru.itis.kpfu.homework.databinding.ItemSongBinding
import ru.itis.kpfu.homework.model.Song

class SongHolder(
    private val binding: ItemSongBinding,
    private val glide: RequestManager,
    private val action: (Song) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    private val option = RequestOptions
        .diskCacheStrategyOf(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)

    fun onBind(song: Song) {
        with(binding) {
            tvTitle.text = song.audioTitle
            tvAuthor.text = song.author
            glide
                .load(song.cover)
                .apply(option)
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(ivCover)

            root.setOnClickListener {
                action(song)
            }
        }
    }

}
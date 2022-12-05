package ru.itis.kpfu.homework.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import ru.itis.kpfu.homework.databinding.ItemSongBinding
import ru.itis.kpfu.homework.model.Song

class SongAdapter(
    private val list: List<Song>,
    private val glide: RequestManager,
    private val action: (Song) -> Unit,
) : RecyclerView.Adapter<SongHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SongHolder = SongHolder(
        binding = ItemSongBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        glide = glide,
        action = action
    )

    override fun onBindViewHolder(
        holder: SongHolder,
        position: Int
    ) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

}
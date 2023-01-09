package ru.itis.kpfu.homework.adapter

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import ru.itis.kpfu.homework.R
import ru.itis.kpfu.homework.model.MainItem
import java.lang.IllegalStateException

class FilmAdapter(
    private val glide: RequestManager,
    private val delete: (MainItem.FilmUiModel) -> Unit,
) : ListAdapter<MainItem, RecyclerView.ViewHolder>(FilmDiff) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder = when(viewType) {
        R.layout.item_ads -> AdsHolder.create(parent)
        R.layout.item_film -> FilmHolder.create(parent, glide, delete)
        else -> throw IllegalStateException("Not implement view type")
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when(val item = getItem(position)) {
            is MainItem.Ads -> (holder as AdsHolder).onBind(item.ads)
            is MainItem.FilmUiModel -> (holder as FilmHolder).onBind(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
             is MainItem.Ads -> R.layout.item_ads
             is MainItem.FilmUiModel -> R.layout.item_film
        }
    }
}
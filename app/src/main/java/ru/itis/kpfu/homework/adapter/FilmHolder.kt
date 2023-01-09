package ru.itis.kpfu.homework.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Priority
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import ru.itis.kpfu.homework.R
import ru.itis.kpfu.homework.databinding.ItemFilmBinding
import ru.itis.kpfu.homework.model.MainItem.FilmUiModel

class FilmHolder(
    private val binding: ItemFilmBinding,
    private val glide: RequestManager,
    private val delete: (FilmUiModel) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    private var film: FilmUiModel? = null

    private val option = RequestOptions
        .diskCacheStrategyOf(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)

    init {
        binding.ivDelete.setOnClickListener {
            film?.also(delete)
        }
    }

    fun onBind(film: FilmUiModel) {
        this.film = film
        with(binding) {
            tvTitle.text = film.title
            tvYear.text = film.year.toString()
            tvGenre.text = film.genre
            tvRate.text = film.rate.toString()
            ivDelete.setImageResource(R.drawable.ic_baseline_delete_24)
            glide
                .load(film.cover)
                .apply(option)
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(ivCover)
        }
    }

    companion object {

        fun create(
            parent: ViewGroup,
            glide: RequestManager,
            delete: (FilmUiModel) -> Unit,
        ): FilmHolder = FilmHolder(
            binding = ItemFilmBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            glide = glide,
            delete =  delete
        )

    }

}
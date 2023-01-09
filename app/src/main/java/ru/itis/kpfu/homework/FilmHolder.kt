package ru.itis.kpfu.homework

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import ru.itis.kpfu.homework.databinding.ItemFilmBinding

class FilmHolder(
    private val binding: ItemFilmBinding,
    private val glide: RequestManager,
    private val action: (Film) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    private val option = RequestOptions
        .diskCacheStrategyOf(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)

    fun onBind(film: Film) {
        with(binding) {
            tvTitle.text = film.title
            tvYear.text = film.year.toString()
            tvGenre.text = film.genre
            tvRate.text = film.rate.toString()

            glide
                .load(film.cover)
                .apply(option)
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(ivCover)

            root.setOnClickListener {
                action(film)
            }
        }

    }

}
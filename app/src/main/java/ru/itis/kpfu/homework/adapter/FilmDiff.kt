package ru.itis.kpfu.homework.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.itis.kpfu.homework.model.MainItem

object FilmDiff : DiffUtil.ItemCallback<MainItem>(){
    override fun areItemsTheSame(
        oldItem: MainItem,
        newItem: MainItem
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: MainItem,
        newItem: MainItem
    ): Boolean = oldItem == newItem
}
package ru.itis.kpfu.homework.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.itis.kpfu.homework.databinding.ItemAdsBinding

class AdsHolder(
    private val binding: ItemAdsBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(title: String) {
        binding.tvAdsTitle.text = title
    }

    companion object {
        fun create(parent: ViewGroup): AdsHolder = AdsHolder(
            binding = ItemAdsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

}
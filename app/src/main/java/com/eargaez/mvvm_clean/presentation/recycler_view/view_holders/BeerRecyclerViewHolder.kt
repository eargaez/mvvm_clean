package com.eargaez.mvvm_clean.presentation.recycler_view.view_holders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eargaez.mvvm_clean.databinding.ItemBeerBinding
import com.eargaez.mvvm_clean.domain.models.Beer

class BeerRecyclerViewHolder (
    private val binding: ItemBeerBinding
) : RecyclerView.ViewHolder(binding.root) {
    var itemClickListener: ((item: Beer)->Unit)? = null

    fun bind(beer: Beer) {
        binding.beer = beer
        Glide.with(binding.root.context)
            .load(beer.imageUrl)
            .into(binding.imageView)
    }
}
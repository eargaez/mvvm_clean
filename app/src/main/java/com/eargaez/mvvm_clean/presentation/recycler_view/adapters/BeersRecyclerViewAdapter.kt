package com.eargaez.mvvm_clean.presentation.recycler_view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eargaez.mvvm_clean.databinding.ItemBeerBinding
import com.eargaez.mvvm_clean.domain.models.Beer
import com.eargaez.mvvm_clean.presentation.recycler_view.view_holders.BeerRecyclerViewHolder

class BeersRecyclerViewAdapter : RecyclerView.Adapter<BeerRecyclerViewHolder>() {

    var items = arrayListOf<Beer>()

    var itemClickListener: ((item: Beer)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerRecyclerViewHolder {
        return BeerRecyclerViewHolder(
            ItemBeerBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: BeerRecyclerViewHolder, position: Int) {
        holder.itemClickListener = itemClickListener
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
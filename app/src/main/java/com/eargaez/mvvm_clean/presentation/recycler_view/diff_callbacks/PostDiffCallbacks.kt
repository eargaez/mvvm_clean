package com.eargaez.mvvm_clean.presentation.recycler_view.diff_callbacks

import androidx.recyclerview.widget.DiffUtil
import com.eargaez.mvvm_clean.domain.models.Beer

class PostDiffCallbacks(
    private val newPosts: List<Beer>,
    private val oldPosts: List<Beer>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldPosts.size

    override fun getNewListSize(): Int = newPosts.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPosts[oldItemPosition].id == newPosts[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPosts[oldItemPosition] == newPosts[newItemPosition]
    }
}
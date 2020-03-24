package com.mobiaxe.home.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mobiaxe.core.data.ReleaseDate
import com.mobiaxe.home.databinding.ItemRecentlyReleasedBinding
import com.mobiaxe.home.BR

class RecentlyReleasedListAdapter : PagedListAdapter<ReleaseDate, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GameBindingViewHolder(ItemRecentlyReleasedBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            (holder as GameBindingViewHolder).bind(it)
        }

    }

    class DiffCallback : DiffUtil.ItemCallback<ReleaseDate>() {
        override fun areItemsTheSame(oldItem: ReleaseDate, newItem: ReleaseDate): Boolean = false

        override fun areContentsTheSame(oldItem: ReleaseDate, newItem: ReleaseDate): Boolean = false
    }

    class GameBindingViewHolder(private val binding: ItemRecentlyReleasedBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ReleaseDate) {
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
        }
    }

}
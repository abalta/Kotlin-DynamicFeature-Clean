package com.mobiaxe.home.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mobiaxe.core.data.Game
import com.mobiaxe.core.data.Status
import com.mobiaxe.home.databinding.ItemGameBinding
import com.mobiaxe.home.databinding.ItemNetworkStateBinding
import com.mobiaxe.home.BR

class GameListAdapter : PagedListAdapter<Game, RecyclerView.ViewHolder>(DiffCallback()) {

    companion object {
        private const val DATA_VIEW_TYPE = 1
        private const val PROGRESS_VIEW_TYPE = 2
    }

    private var networkState: Status? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == PROGRESS_VIEW_TYPE) {
            NetworkStateItemViewHolder(ItemNetworkStateBinding.inflate(layoutInflater, parent, false))
        } else {
            GameBindingViewHolder(ItemGameBinding.inflate(layoutInflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is GameBindingViewHolder) {
            getItem(position)?.let {
                holder.bind(it)
            }
        } else {
            (holder as NetworkStateItemViewHolder).bind(networkState)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            PROGRESS_VIEW_TYPE
        } else {
            DATA_VIEW_TYPE
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    private fun hasExtraRow() = networkState != null && networkState != Status.SUCCESS


    fun setNetworkState(newNetworkState: Status?) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Game>() {
        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean = oldItem == newItem
    }

    class GameBindingViewHolder(private val binding: ItemGameBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Game) {
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
        }
    }

    class NetworkStateItemViewHolder(private val binding: ItemNetworkStateBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Status?) {
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
        }
    }

}
package com.mobiaxe.core.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobiaxe.core.BR

abstract class DataBindingAdapter<T>(diffCallback: DiffUtil.ItemCallback<T>) :
        ListAdapter<T, DataBindingAdapter.DataBindingViewHolder<T>>(diffCallback) {

    private var onListItemViewClickListener: OnListItemViewClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        return DataBindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder<T>, position: Int) = holder.bind(getItem(position))

    class DataBindingViewHolder<T>(private val binding: ViewDataBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(item: T) {
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
        }
    }

    interface OnListItemViewClickListener{
        fun onClick(view: View, position: Int)
    }
}
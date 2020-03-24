package com.mobiaxe.core.presentation

abstract class ListItemViewModel{
    var adapterPosition: Int = -1
    var onListItemViewClickListener: DataBindingAdapter.OnListItemViewClickListener? = null
}
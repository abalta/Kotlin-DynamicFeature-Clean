package com.mobiaxe.wasd.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.mobiaxe.core.presentation.DataBindingAdapter

@BindingAdapter("app:isSelected")
fun MaterialCardView.setSelected(isSelected: Boolean) {
    isChecked = isSelected
    isHovered = isSelected
    isPressed = isSelected
}

@BindingAdapter("app:recycledViewPool")
fun RecyclerView.setRecyclerViewPoolBinding(pool: RecyclerView.RecycledViewPool) {
    setRecycledViewPool(pool)
}

@BindingAdapter("app:submitList")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, items: List<T>?) {
    if (recyclerView.adapter is DataBindingAdapter<*>) {
        items?.let {
            (recyclerView.adapter as DataBindingAdapter<T>).submitList(it)
        }
    }
}
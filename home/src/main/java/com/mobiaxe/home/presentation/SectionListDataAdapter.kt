package com.mobiaxe.home.presentation

import androidx.recyclerview.widget.DiffUtil
import com.mobiaxe.core.presentation.DataBindingAdapter
import com.mobiaxe.home.R
import com.mobiaxe.home.data.SectionData

class SectionListDataAdapter: DataBindingAdapter<SectionData>(DiffCallback()) {

    class DiffCallback: DiffUtil.ItemCallback<SectionData>() {
        override fun areItemsTheSame(oldItem: SectionData, newItem: SectionData): Boolean = false

        override fun areContentsTheSame(oldItem: SectionData, newItem: SectionData): Boolean = false
    }

    override fun getItemViewType(position: Int) = R.layout.item_section
}
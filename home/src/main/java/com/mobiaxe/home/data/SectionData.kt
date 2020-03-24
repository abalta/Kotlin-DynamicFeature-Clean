package com.mobiaxe.home.data

import androidx.recyclerview.widget.RecyclerView
import com.mobiaxe.core.presentation.ListItemViewModel
import com.mobiaxe.home.presentation.GameListAdapter
import com.mobiaxe.home.presentation.RecentlyReleasedListAdapter

data class SectionData(
        val headerTitle: Int,
        val gameListAdapter: GameListAdapter?,
        val recentlyReleasedListAdapter: RecentlyReleasedListAdapter?,
        val pool: RecyclerView.RecycledViewPool
): ListItemViewModel()
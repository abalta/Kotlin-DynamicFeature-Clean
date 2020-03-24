package com.mobiaxe.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.mobiaxe.core.data.Game
import com.mobiaxe.core.data.Status
import com.mobiaxe.core.presentation.BaseViewModel
import com.mobiaxe.home.R
import com.mobiaxe.home.data.SectionData
import com.mobiaxe.home.domain.datasource.GamesDataSourceFactory
import com.mobiaxe.home.domain.usecase.GetPopularGames

class HomeViewModel(
    private val getPopularGames: GetPopularGames
): BaseViewModel(getPopularGames) {

    private val _sectionListLiveData: MutableLiveData<List<SectionData>> = MutableLiveData()
    val sectionListLiveData: LiveData<List<SectionData>> = _sectionListLiveData

    lateinit var popularGamesPagedList: LiveData<PagedList<Game>>

    var networkState: LiveData<Status>? = null

    fun getDashboard() {
        val popularGamesDataFactory = GamesDataSourceFactory(getPopularGames)

        val config = PagedList.Config.Builder()
            .setPageSize(50)
            .setPrefetchDistance(20)
            .setEnablePlaceholders(false)
            .build()

        popularGamesPagedList = LivePagedListBuilder(popularGamesDataFactory, 50).build()

        getSection(mutableListOf(
            SectionData(R.string.title_popular_games, GameListAdapter(), null, RecyclerView.RecycledViewPool())))

        networkState = Transformations.switchMap(popularGamesDataFactory.gameDataSourceLiveData) {
            it.networkState
        }
    }

    private fun getSection(sectionDataList: MutableList<SectionData>) {
        _sectionListLiveData.value = sectionDataList
    }

}
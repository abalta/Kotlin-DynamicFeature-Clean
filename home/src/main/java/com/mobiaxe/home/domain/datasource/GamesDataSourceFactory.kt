package com.mobiaxe.home.domain.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.mobiaxe.core.data.Game
import com.mobiaxe.home.data.datasource.GamesPagingDataSource
import com.mobiaxe.home.domain.usecase.GetPopularGames

class GamesDataSourceFactory(private val getPopularGames: GetPopularGames): DataSource.Factory<Int, Game>() {

    val gameDataSourceLiveData = MutableLiveData<GamesPagingDataSource>()

    override fun create(): DataSource<Int, Game> {
        val dataSource = GamesPagingDataSource(
            getPopularGames
        )
        gameDataSourceLiveData.postValue(dataSource)
        return dataSource
    }
}
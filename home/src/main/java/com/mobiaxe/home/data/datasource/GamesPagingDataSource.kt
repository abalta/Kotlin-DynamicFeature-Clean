package com.mobiaxe.home.data.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.mobiaxe.core.data.Game
import com.mobiaxe.core.data.Status
import com.mobiaxe.home.domain.usecase.GetPopularGames

class GamesPagingDataSource(private val getPopularGames: GetPopularGames) : PageKeyedDataSource<Int, Game>() {

    private val _networkState = MutableLiveData<Status>()
    val networkState: LiveData<Status> = _networkState

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Game>) {
        _networkState.postValue(Status.LOADING)
        getPopularGames.execute(0) {
            callback.onResult(it, null, 0)
            _networkState.postValue(Status.SUCCESS)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Game>) {
        _networkState.postValue(Status.LOADING)
        getPopularGames.execute(params.key + 20) {
            callback.onResult(it, params.key + 20)
            _networkState.postValue(Status.SUCCESS)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Game>) {

    }
}
package com.mobiaxe.home.domain.datasource

import com.mobiaxe.core.data.Game
import com.mobiaxe.core.remote.WASDRemoteDataStore
import com.mobiaxe.home.data.datasource.HomeRemoteDataSource

class HomeRemoteDataSourceImpl(private val wasdRemoteDataStore: WASDRemoteDataStore): HomeRemoteDataSource {
    override suspend fun popularGamesFetched(offset: Int): List<Game> = wasdRemoteDataStore.getPopularGames(offset, "(48, 6)")
}
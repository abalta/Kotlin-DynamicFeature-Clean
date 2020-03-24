package com.mobiaxe.home.data.repository

import com.mobiaxe.core.data.Game
import com.mobiaxe.home.data.datasource.HomeRemoteDataSource
import com.mobiaxe.home.domain.repository.HomeRepository

class HomeDataRepository(private val homeRemoteDataSource: HomeRemoteDataSource): HomeRepository {
    override suspend fun getPopularGames(offset: Int): List<Game> = homeRemoteDataSource.popularGamesFetched(offset)
}
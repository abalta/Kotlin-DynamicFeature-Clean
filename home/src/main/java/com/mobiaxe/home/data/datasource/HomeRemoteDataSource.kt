package com.mobiaxe.home.data.datasource

import com.mobiaxe.core.data.Game

interface HomeRemoteDataSource {
    suspend fun popularGamesFetched(offset: Int): List<Game>
}
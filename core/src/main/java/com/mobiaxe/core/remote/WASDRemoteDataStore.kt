package com.mobiaxe.core.remote

import com.mobiaxe.core.data.Game

interface WASDRemoteDataStore {
    suspend fun getPopularGames(offset: Int, platforms: String): List<Game>
}
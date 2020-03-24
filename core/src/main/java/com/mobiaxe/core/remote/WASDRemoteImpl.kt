package com.mobiaxe.core.remote

import com.mobiaxe.core.data.Game
import com.mobiaxe.core.remote.service.WASDService

class WASDRemoteImpl(private val wasdService: WASDService): WASDRemoteDataStore {

    companion object {
        private const val POPULAR_GAMES_FIELDS = "name,popularity,rating,cover.image_id,platforms"
        private const val POPULAR_GAMES_SORT = "popularity"
        private const val COVER_FIELDS = "image_id,url,game"
        private const val GAME = "game"
        private const val DATE = "date"
        private const val LIMIT = 50
    }

    private val whereGames: (String) -> String = {param -> "platforms = $param & category = 0"}

    override suspend fun getPopularGames(offset: Int, platforms: String): List<Game> {
        val request = IGDBRequest.Builder().fields(POPULAR_GAMES_FIELDS).sort(POPULAR_GAMES_SORT).where(whereGames(platforms)).limit(LIMIT).offset(offset).build()
        return wasdService.getGames(request)
    }
}
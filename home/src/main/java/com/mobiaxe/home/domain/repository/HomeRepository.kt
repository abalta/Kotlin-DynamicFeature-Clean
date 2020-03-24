package com.mobiaxe.home.domain.repository

import com.mobiaxe.core.data.Game

interface HomeRepository {
    suspend fun getPopularGames(offset: Int): List<Game>
}
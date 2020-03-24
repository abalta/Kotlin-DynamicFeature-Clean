package com.mobiaxe.home.domain.usecase

import com.mobiaxe.core.data.Game
import com.mobiaxe.core.domain.SuspendUseCase
import com.mobiaxe.home.domain.repository.HomeRepository

class GetPopularGames(
    private val homeRepository: HomeRepository
): SuspendUseCase<List<Game>, Int>() {
    override suspend fun run(params: Int): List<Game> =
        homeRepository.getPopularGames(params)
}
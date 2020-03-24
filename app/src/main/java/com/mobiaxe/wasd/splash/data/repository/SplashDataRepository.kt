package com.mobiaxe.wasd.splash.data.repository

import com.mobiaxe.wasd.splash.data.datasource.SplashCachedDataSource
import com.mobiaxe.wasd.splash.domain.repository.SplashRepository

class SplashDataRepository(private val splashCachedDataSource: SplashCachedDataSource): SplashRepository {

    override fun isOnboardingShow(isShown: Boolean) {
        splashCachedDataSource.isOnboardingShown(isShown)
    }

    override fun isOnboardingPass(): Boolean =
            splashCachedDataSource.isOnboardingPassed()

}
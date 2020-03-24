package com.mobiaxe.wasd.splash.domain.datasource

import com.mobiaxe.core.cache.PreferencesHelper
import com.mobiaxe.wasd.splash.data.datasource.SplashCachedDataSource

class SplashCachedDataSourceImpl constructor(private val preferencesHelper: PreferencesHelper): SplashCachedDataSource {

    override fun isOnboardingShown(isShowOnboarding: Boolean) {
        preferencesHelper.onboardingShow = isShowOnboarding
    }

    override fun isOnboardingPassed(): Boolean =
        preferencesHelper.onboardingShow

}
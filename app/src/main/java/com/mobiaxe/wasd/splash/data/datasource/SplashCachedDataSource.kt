package com.mobiaxe.wasd.splash.data.datasource

interface SplashCachedDataSource {

    fun isOnboardingShown(isShowOnboarding: Boolean)

    fun isOnboardingPassed(): Boolean

}
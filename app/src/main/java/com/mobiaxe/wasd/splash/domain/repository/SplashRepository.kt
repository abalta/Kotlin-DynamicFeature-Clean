package com.mobiaxe.wasd.splash.domain.repository

interface SplashRepository {

    fun isOnboardingShow(isShown: Boolean)

    fun isOnboardingPass(): Boolean

}
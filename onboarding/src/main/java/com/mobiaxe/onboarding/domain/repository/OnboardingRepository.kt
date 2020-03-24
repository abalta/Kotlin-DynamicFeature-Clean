package com.mobiaxe.onboarding.domain.repository


interface OnboardingRepository {

    suspend fun savePlatform(platformId: String?)

}
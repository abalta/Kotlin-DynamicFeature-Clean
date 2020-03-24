package com.mobiaxe.onboarding.data.datasource

interface OnboardingCachedDataSource {

    suspend fun platformSaved(platformId: String?)

}
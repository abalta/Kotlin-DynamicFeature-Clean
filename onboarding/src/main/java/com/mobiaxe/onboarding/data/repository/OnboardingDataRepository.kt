package com.mobiaxe.onboarding.data.repository

import com.mobiaxe.onboarding.data.datasource.OnboardingCachedDataSource
import com.mobiaxe.onboarding.domain.repository.OnboardingRepository

class OnboardingDataRepository(private val onboardingCachedDataSource: OnboardingCachedDataSource): OnboardingRepository {

    override suspend fun savePlatform(platformId: String?) =
        onboardingCachedDataSource.platformSaved(platformId)
}
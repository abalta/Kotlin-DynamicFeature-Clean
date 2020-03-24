package com.mobiaxe.onboarding.domain.datasource

import com.mobiaxe.core.cache.WASDCachedDataStore
import com.mobiaxe.onboarding.data.datasource.OnboardingCachedDataSource

class OnboardingCachedDataSourceImpl(private val wasdCachedDataStore: WASDCachedDataStore): OnboardingCachedDataSource {

    override suspend fun platformSaved(platformId: String?) =
        wasdCachedDataStore.savePlatform(platformId)

}
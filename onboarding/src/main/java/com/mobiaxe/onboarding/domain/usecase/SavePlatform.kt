package com.mobiaxe.onboarding.domain.usecase

import com.mobiaxe.core.domain.SuspendUseCase
import com.mobiaxe.onboarding.domain.repository.OnboardingRepository

class SavePlatform(
    private val onboardingRepository: OnboardingRepository): SuspendUseCase<Unit, String>() {

    override suspend fun run(params: String) = onboardingRepository.savePlatform(params)

}
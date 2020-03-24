package com.mobiaxe.wasd.splash.domain.usecase

import com.mobiaxe.core.domain.SynchronouosUseCase
import com.mobiaxe.wasd.splash.domain.repository.SplashRepository

class OnboardingPassed(
    private val splashRepository: SplashRepository
): SynchronouosUseCase<Boolean, Boolean> {

    override fun execute(params: Boolean): Boolean {
        return if (splashRepository.isOnboardingPass()) {
            true
        } else {
            splashRepository.isOnboardingShow(params)
            params
        }
    }
}
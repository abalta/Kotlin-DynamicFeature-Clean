package com.mobiaxe.wasd.splash.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobiaxe.core.presentation.BaseViewModel
import com.mobiaxe.wasd.splash.domain.usecase.OnboardingPassed

class SplashViewModel(
    private val onboardingPassed: OnboardingPassed
): BaseViewModel() {
    private val _showOnboardingLiveData = MutableLiveData<Boolean>()
    val showOnboardingLiveData: LiveData<Boolean> = _showOnboardingLiveData

    fun isOnboardingPassed() {
        _showOnboardingLiveData.value = onboardingPassed.execute(false)
    }
}
package com.mobiaxe.wasd.navigation

import android.content.Intent

object Navigation {

    private const val ONBOARDING = "com.mobiaxe.onboarding.presentation.OnboardingActivity"
    private const val DASHBOARD = "com.mobiaxe.wasd.dashboard.DashboardActivity"

    fun navigateOnboardingOrDashboard(isNavigateDashboard: Boolean): Intent? =
        if (isNavigateDashboard) {
            DASHBOARD.loadIntentOrNull()
        } else {
            ONBOARDING.loadIntentOrNull()
        }

}
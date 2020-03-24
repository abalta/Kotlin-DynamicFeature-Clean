package com.mobiaxe.wasd.splash.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobiaxe.core.extension.observe
import com.mobiaxe.core.extension.start
import com.mobiaxe.wasd.navigation.Navigation
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(getViewModel<SplashViewModel>()) {
            isOnboardingPassed()
            observe(showOnboardingLiveData) {
                start(Navigation.navigateOnboardingOrDashboard(it))
                finish()
            }
        }

    }
}
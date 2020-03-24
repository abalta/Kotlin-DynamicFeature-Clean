package com.mobiaxe.onboarding.koin

import com.mobiaxe.onboarding.data.datasource.OnboardingCachedDataSource
import com.mobiaxe.onboarding.data.repository.OnboardingDataRepository
import com.mobiaxe.onboarding.domain.datasource.OnboardingCachedDataSourceImpl
import com.mobiaxe.onboarding.domain.repository.OnboardingRepository
import com.mobiaxe.onboarding.domain.usecase.SavePlatform
import com.mobiaxe.onboarding.presentation.OnboardingActivity
import com.mobiaxe.onboarding.presentation.OnboardingAdapter
import com.mobiaxe.onboarding.presentation.OnboardingViewModel
import com.mobiaxe.onboarding.presentation.ParallaxPageTransformer
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module

fun loadModules() = loadKoinModules(
    listOf(
        viewModelModule,
        onboardingModule,
        repositoryModule,
        useCaseModule
    )
)

fun unloadModules() = unloadKoinModules(
    listOf(
        viewModelModule,
        onboardingModule,
        repositoryModule,
        useCaseModule
    )
)

val viewModelModule = module(override = true) {
    viewModel { OnboardingViewModel(androidApplication(), get(), get()) }
}

val onboardingModule = module(override = true) {
    factory { ParallaxPageTransformer() }
    factory { (activity: OnboardingActivity) -> OnboardingAdapter(activity) }
    factory { (activity: OnboardingActivity) -> activity.ViewPager2PageChangeCallback() }
}

val repositoryModule = module {
    factory<OnboardingRepository> { OnboardingDataRepository(get()) }
    factory<OnboardingCachedDataSource> { OnboardingCachedDataSourceImpl(get()) }
}

val useCaseModule = module {
    factory { SavePlatform(get()) }
}

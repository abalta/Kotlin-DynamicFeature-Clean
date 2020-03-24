package com.mobiaxe.wasd.koin

import com.mobiaxe.wasd.splash.data.datasource.SplashCachedDataSource
import com.mobiaxe.wasd.splash.data.repository.SplashDataRepository
import com.mobiaxe.wasd.splash.domain.datasource.SplashCachedDataSourceImpl
import com.mobiaxe.wasd.splash.domain.repository.SplashRepository
import com.mobiaxe.wasd.splash.domain.usecase.OnboardingPassed
import com.mobiaxe.wasd.splash.presentation.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
}

val useCaseModule = module {
    factory { OnboardingPassed(get()) }
}

val repositoryModule = module {
    factory<SplashRepository> { SplashDataRepository(get()) }
    factory<SplashCachedDataSource> { SplashCachedDataSourceImpl(get()) }
}
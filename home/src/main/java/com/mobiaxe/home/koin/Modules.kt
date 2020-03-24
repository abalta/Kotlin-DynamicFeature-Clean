package com.mobiaxe.home.koin

import com.mobiaxe.home.data.datasource.HomeRemoteDataSource
import com.mobiaxe.home.data.repository.HomeDataRepository
import com.mobiaxe.home.domain.datasource.HomeRemoteDataSourceImpl
import com.mobiaxe.home.domain.repository.HomeRepository
import com.mobiaxe.home.domain.usecase.GetPopularGames
import com.mobiaxe.home.presentation.HomeFragment
import com.mobiaxe.home.presentation.HomeViewModel
import com.mobiaxe.home.presentation.SectionListDataAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module

fun loadModules() = loadKoinModules(
    listOf(
        viewModelModule,
        repositoryModule,
        useCaseModule,
        homeModule
    )
)

fun unloadModules() = unloadKoinModules(
    listOf(
        viewModelModule,
        repositoryModule,
        useCaseModule,
        homeModule
    )
)

val homeModule = module(override = true) {
    factory { SectionListDataAdapter() }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}

val repositoryModule = module {
    factory<HomeRepository> { HomeDataRepository(get()) }
    factory<HomeRemoteDataSource> { HomeRemoteDataSourceImpl(get()) }
}

val useCaseModule = module {
    factory { GetPopularGames(get()) }
}

package com.mobiaxe.core.koin

import androidx.room.Room
import com.mobiaxe.core.BuildConfig
import com.mobiaxe.core.cache.PreferencesHelper
import com.mobiaxe.core.cache.WASDCacheImpl
import com.mobiaxe.core.cache.WASDCachedDataStore
import com.mobiaxe.core.cache.db.WASDDatabase
import com.mobiaxe.core.remote.WASDRemoteDataStore
import com.mobiaxe.core.remote.WASDRemoteImpl
import com.mobiaxe.core.remote.service.WASDServiceFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreModule = module {
    single { PreferencesHelper(androidContext()) }
    single {
        Room.databaseBuilder(androidContext(),
            WASDDatabase::class.java, "WASD.db")
            .build()
    }

    factory { WASDServiceFactory.makeWASDService(BuildConfig.DEBUG, BuildConfig.GRADLE_API_BASE_URL, BuildConfig.GRADLE_API_TOKEN) }
    factory { get<WASDDatabase>().cachedWASDDao() }
    factory<WASDCachedDataStore> { WASDCacheImpl(get()) }
    factory<WASDRemoteDataStore> { WASDRemoteImpl(get()) }
}
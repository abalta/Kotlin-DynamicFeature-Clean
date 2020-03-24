package com.mobiaxe.wasd

import android.app.Application
import com.facebook.stetho.Stetho
import com.mobiaxe.core.koin.coreModule
import com.mobiaxe.wasd.koin.repositoryModule
import com.mobiaxe.wasd.koin.useCaseModule
import com.mobiaxe.wasd.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WASDApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WASDApplication)
            modules(listOf(
                coreModule,
                viewModelModule,
                useCaseModule,
                repositoryModule
            ))
        }
        Stetho.initializeWithDefaults(this)
    }

}
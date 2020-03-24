package com.mobiaxe.core.cache

interface WASDCachedDataStore {

    suspend fun savePlatform(platformId: String?)

}
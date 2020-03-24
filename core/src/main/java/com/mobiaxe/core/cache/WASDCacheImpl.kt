package com.mobiaxe.core.cache

import com.mobiaxe.core.cache.db.WASDDatabase
import com.mobiaxe.core.data.Gamer

class WASDCacheImpl(private val wasdDatabase: WASDDatabase): WASDCachedDataStore {

    override suspend fun savePlatform(platformId: String?) =
        wasdDatabase.cachedWASDDao().insertPlatformId(platformId?.let { Gamer(platform = it) })
}
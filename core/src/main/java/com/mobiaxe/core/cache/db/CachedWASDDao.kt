package com.mobiaxe.core.cache.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.mobiaxe.core.data.Gamer

@Dao
abstract class CachedWASDDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertPlatformId(platform: Gamer?)

}
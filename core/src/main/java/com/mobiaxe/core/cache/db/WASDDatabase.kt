package com.mobiaxe.core.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mobiaxe.core.data.Gamer

@Database(entities = [Gamer::class], version = 1)
abstract class WASDDatabase: RoomDatabase() {

    abstract fun cachedWASDDao(): CachedWASDDao

    companion object {

        @Volatile
        private var INSTANCE: WASDDatabase? = null

        fun getInstance(context: Context): WASDDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                WASDDatabase::class.java, "WASD.db")
                .build()
    }
}
package com.mobiaxe.core.cache.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mobiaxe.core.data.Cover

class CoverConverter {

    @TypeConverter
    fun coverToJson(value: Cover?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToCover(value: String): Cover? {
        return Gson().fromJson(value, Cover::class.java) ?: Cover(0, "", "", 0)
    }
}
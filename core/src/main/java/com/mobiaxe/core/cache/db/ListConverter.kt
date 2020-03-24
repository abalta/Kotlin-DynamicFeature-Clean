package com.mobiaxe.core.cache.db

import androidx.room.TypeConverter
import com.google.gson.Gson

class ListConverter {

    @TypeConverter
    fun listToJson(value: List<Int>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<Int>? {
        val objects = Gson().fromJson(value, Array<Int>::class.java)
        return if (objects == null) {
            emptyList()
        } else {
            objects as Array<Int>
            objects.toList()
        }
    }
}
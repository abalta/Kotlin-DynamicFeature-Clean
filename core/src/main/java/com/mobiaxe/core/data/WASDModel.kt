package com.mobiaxe.core.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.mobiaxe.core.cache.db.CoverConverter
import com.mobiaxe.core.cache.db.ListConverter
import com.mobiaxe.core.cache.db.WASDConstants
import com.mobiaxe.core.presentation.ListItemViewModel
import java.util.*

@Entity(tableName = WASDConstants.TABLE_GAMER)
data class Gamer(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "platform")
    val platform: String
)

@Entity(tableName = WASDConstants.TABLE_POPULAR)
data class Game(
    @PrimaryKey @field:SerializedName("id") val id: Long,
    @TypeConverters(CoverConverter::class) @field:SerializedName("cover") val cover: Cover?,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("popularity") val popularity: Double,
    @TypeConverters(ListConverter::class) val platforms: MutableList<Int> = mutableListOf()
): ListItemViewModel()

data class Cover(
    @SerializedName("id") val id: Long,
    @SerializedName("image_id") val imageId: String,
    @SerializedName("url") val url: String,
    @SerializedName("game") val game: Long)

data class ReleaseDate(
    @SerializedName("id") val id: Long,
    @SerializedName("game") val game: Game
): ListItemViewModel()
package com.jabukaprog.family.data.databases

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "photo_table")
data class PhotoModelEntity(
    @PrimaryKey(autoGenerate = true) var id: Long? = 0L,
    @ColumnInfo(name = "filename") var name: String? = "",
    @ColumnInfo(name = "full_path") var path: String? = "",
    @ColumnInfo(name = "parent_path") var parentPath: String? = "",
    @ColumnInfo(name = "last_modified") var modified: Long? = 0L,
    @ColumnInfo(name = "date_taken") var taken: Long? = 0L,
    @ColumnInfo(name = "size") var size: Long? = 0L,
    @ColumnInfo(name = "is_favorite") var isFavorite: Boolean? = false,
)
package com.jabukaprog.family.data.databases

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PhotoModelEntity::class], version = 1, exportSchema = false)
abstract class GalleryDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}

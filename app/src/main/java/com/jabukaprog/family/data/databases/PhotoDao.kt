package com.jabukaprog.family.data.databases

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photo_table")
    suspend fun getPhotos(): List<PhotoModelEntity>

    @Query("SELECT * FROM photo_table")
    fun getPhotosForTest(): LiveData<List<PhotoModelEntity>>

    @Insert(onConflict = REPLACE)
    suspend fun insert(photoModelItem: PhotoModelEntity)

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(photoList: List<PhotoModelEntity>)

    @Delete
    fun deletePhoto(photoModelItem: PhotoModelEntity)

}

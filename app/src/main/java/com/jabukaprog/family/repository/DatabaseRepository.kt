package com.jabukaprog.family.repository

import com.jabukaprog.family.data.databases.PhotoDao
import com.jabukaprog.family.data.databases.PhotoModelEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DatabaseRepository(
    private val mediumDao: PhotoDao
) {

    fun insertPhotoModelDB(photoModelDB: PhotoModelEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            mediumDao.insert(photoModelDB)
        }
    }

    suspend fun getPhotos(): List<PhotoModelEntity> {
        return mediumDao.getPhotos()
    }

}
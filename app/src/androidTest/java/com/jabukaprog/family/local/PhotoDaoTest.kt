package com.jabukaprog.family.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.jabukaprog.family.data.databases.GalleryDatabase
import com.jabukaprog.family.data.databases.PhotoDao
import com.jabukaprog.family.data.databases.PhotoModelEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class PhotoDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database : GalleryDatabase
    private lateinit var dao : PhotoDao

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            GalleryDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.photoDao()
    }

    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun insert() = runBlockingTest{
        val photoItem = PhotoModelEntity(0L,"photoName", "path", "parentPath", 0L)
        dao.insert(photoItem)

        val allPhotos = dao.getPhotosForTest().getOrAwaitValue()
        assertThat(allPhotos).contains(photoItem)
    }

    @Test
    fun deletePhotoItem() = runBlockingTest {
        val photoItem = PhotoModelEntity(0L,"photoName", "path", "parentPath", 0L)
        dao.insert(photoItem)
        dao.deletePhoto(photoItem)
        val allPhotos = dao.getPhotosForTest().getOrAwaitValue()
        assertThat(allPhotos).doesNotContain(photoItem)
    }

}
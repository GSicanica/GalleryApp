package com.jabukaprog.family.local.hilt

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import com.jabukaprog.family.data.databases.*
import com.jabukaprog.family.di.AppModule
import com.jabukaprog.family.di.DatabaseModule
import com.jabukaprog.family.local.getOrAwaitValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class PhotoDaoHiltTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: GalleryDatabase
    private lateinit var dao: PhotoDao

    @Before
    fun setup(){
        hiltRule.inject()
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
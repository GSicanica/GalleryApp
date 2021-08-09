package com.jabukaprog.family.di

import android.content.Context
import androidx.room.Room
import com.jabukaprog.family.data.databases.GalleryDatabase
import com.jabukaprog.family.data.databases.PhotoDao
import com.jabukaprog.family.repository.DatabaseRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideRunningDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        GalleryDatabase::class.java,
        "gallery_db"
    ).build()

    @Singleton
    @Provides
    fun provideMediumDao(db: GalleryDatabase) = db.photoDao()

    @Provides
    @Reusable
    fun provideDatabaseRepository(
        db: PhotoDao
    ): DatabaseRepository = DatabaseRepository(db)

}









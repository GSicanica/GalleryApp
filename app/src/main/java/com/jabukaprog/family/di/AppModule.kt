package com.jabukaprog.family.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.jabukaprog.family.BaseApplication
import com.jabukaprog.family.data.protodata.ProtoManager
import com.jabukaprog.family.repository.LocalDataRepository
import com.jabukaprog.family.repository.PhotosPagingRepository
import com.jabukaprog.family.utils.date.DateHelper
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }

    @Provides
    @Singleton
    fun provideFirebaseAnalytics(@ApplicationContext context: Context) =
        FirebaseAnalytics.getInstance(context)

    @Provides
    @Singleton
    fun provideFirebaseCrashlytics() = FirebaseCrashlytics.getInstance()

    @Provides
    @Singleton
    fun provideProtoManager(@ApplicationContext context: Context) = ProtoManager(context)

    @Provides
    @Reusable
    fun provideImagineRepository(@ApplicationContext context: Context): PhotosPagingRepository =
        PhotosPagingRepository(context)


    @Provides
    @Reusable
    fun provideDateHelper() = DateHelper()

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @Reusable
    fun provideLocalRepository(
        sharedPreferences: SharedPreferences
    ): LocalDataRepository = LocalDataRepository(sharedPreferences)

}
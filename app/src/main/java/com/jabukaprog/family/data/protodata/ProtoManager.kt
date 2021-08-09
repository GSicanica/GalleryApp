package com.jabukaprog.family.data.protodata

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject


private const val FILE_NAME_SETTINGS = "SettingsPrefFile"
private const val FILE_NAME_FAVORITE= "FavoritePrefFile"
private const val FILE_NAME_ALL_FAVORITE= "AllFavoritePrefFile"

private val Context.internalDataStoreSettings  by dataStore(
    fileName = FILE_NAME_SETTINGS,
    serializer = SettingsProtoSerializer
)

private val Context.internalDataStoreFavorite : DataStore<FavoritePicture> by dataStore(
    fileName = FILE_NAME_FAVORITE,
    serializer = FavoriteProtoSerializer
)

private val Context.internalDataStoreAllFavorite : DataStore<AllFavoritePicture> by dataStore(
    fileName = FILE_NAME_ALL_FAVORITE,
    serializer = AllFavoriteProtoSerializer
)


class ProtoManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    val allSavedFavorite
        get() = context.internalDataStoreAllFavorite.data.map { allFavoritePicture ->
            allFavoritePicture.favoritePictureList
        }

    val allSaved
        get() = context.internalDataStoreFavorite.data.map { favoritePicture ->
            favoritePicture.defaultInstanceForType
        }


    val isAnalyticsEnabled
        get() = context.internalDataStoreSettings.data.map { settings ->
            settings.analyticsEnabled
        }

    val isCrashlyticsEnabled
        get() = context.internalDataStoreSettings.data.map { settings ->
            settings.crashlyticsEnabled
        }


    suspend fun setIsAnalyticsEnabled(isEnabled: Boolean) {
        context.internalDataStoreSettings.updateData { settings ->
            settings.toBuilder()
                .setAnalyticsEnabled(isEnabled)
                .build()
        }
    }

    suspend fun setIsCrashlyticsEnabled(isEnabled: Boolean) {
        context.internalDataStoreSettings.updateData { settings ->
            settings.toBuilder()
                .setCrashlyticsEnabled(isEnabled)
                .build()
        }
    }


    suspend fun setFavoritePicture(index : Int){
        context.internalDataStoreFavorite.updateData { favoritePicture ->
            favoritePicture.toBuilder().build()
        }
    }

}

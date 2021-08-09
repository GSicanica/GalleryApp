package com.jabukaprog.family.data.protodata

import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import timber.log.Timber
import java.io.InputStream
import java.io.OutputStream

@Suppress("BlockingMethodInNonBlockingContext")
object AllFavoriteProtoSerializer : Serializer<AllFavoritePicture> {

    override val defaultValue: AllFavoritePicture
        get() = AllFavoritePicture
                .getDefaultInstance()


    override suspend fun readFrom(input: InputStream): AllFavoritePicture {
        return try {
            AllFavoritePicture.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            Timber.e(e)
            AllFavoriteProtoSerializer.defaultValue
        }
    }

    override suspend fun writeTo(t: AllFavoritePicture, output: OutputStream) {
        t.writeTo(output)
    }
}

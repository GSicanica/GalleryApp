package com.jabukaprog.family.data.protodata

import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import timber.log.Timber
import java.io.InputStream
import java.io.OutputStream

@Suppress("BlockingMethodInNonBlockingContext")
object FavoriteProtoSerializer  : Serializer<FavoritePicture> {

    override val defaultValue: FavoritePicture
        get() = FavoritePicture.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): FavoritePicture {
        return try {
            FavoritePicture.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            Timber.e(e)
            FavoriteProtoSerializer.defaultValue
        }
    }

    override suspend fun writeTo(t: FavoritePicture, output: OutputStream) {
        t.writeTo(output)
    }
}


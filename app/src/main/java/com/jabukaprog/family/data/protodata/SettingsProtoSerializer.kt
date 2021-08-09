package com.jabukaprog.family.data.protodata

import androidx.datastore.core.Serializer
import com.android.family.BuildConfig
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream
import timber.log.Timber

@Suppress("BlockingMethodInNonBlockingContext")
object SettingsProtoSerializer : Serializer<SettingsProto> {

    override val defaultValue: SettingsProto
        get() = SettingsProto.getDefaultInstance()
            .toBuilder()
            .setAnalyticsEnabled(!BuildConfig.DEBUG)
            .setCrashlyticsEnabled(!BuildConfig.DEBUG)
            .build()

    override suspend fun readFrom(input: InputStream): SettingsProto {
        return try {
            SettingsProto.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            Timber.e(e)
            defaultValue
        }
    }

    override suspend fun writeTo(t: SettingsProto, output: OutputStream) {
        t.writeTo(output)
    }
}

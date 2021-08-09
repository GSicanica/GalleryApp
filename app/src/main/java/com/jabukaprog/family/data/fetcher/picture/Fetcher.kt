package com.jabukaprog.family.data.fetcher.picture

import android.content.Context

class Fetcher {
    companion object {
        fun withPictureContext(context: Context?): FetchPicture? {
            return FetchPicture.getInstance(context!!)
        }
    }
}
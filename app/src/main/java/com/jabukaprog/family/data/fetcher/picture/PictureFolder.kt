package com.jabukaprog.family.data.fetcher.picture

import android.os.Parcelable
import com.jabukaprog.family.data.PhotoModel
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue


@Parcelize
data class PictureFolder(
    var folderPath: String? = null,
    var folderName: String? = null,
    var photos: @RawValue ArrayList<PhotoModel>? = null,
    var bucket_id: Int = 0
) : Parcelable {

    constructor() : this("", "", arrayListOf<PhotoModel>(), 0) {
        photos = ArrayList()
    }

    constructor(path: String?, folderName: String?) : this("", "", arrayListOf<PhotoModel>(), 0) {
        folderPath = path
        this.folderName = folderName
        photos = ArrayList()
    }
}
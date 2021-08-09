package com.jabukaprog.family.data

data class PhotoModel(
    val id: Int = 0,
    val name: String = ""
) {
    var pictureName: String? = null
    var picturePath: String? = null
    var assertFileUri: String? = null
    var pictureId: Int = 0
}
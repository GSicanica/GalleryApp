package com.jabukaprog.family.data.fetcher.picture

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import com.jabukaprog.family.data.PhotoModel

class FetchPicture(context: Context) {
    private val pictureContex: Context = context.applicationContext

    @SuppressLint("InlinedApi")
    private val projections = arrayOf(
        MediaStore.Images.Media.DATA,
        MediaStore.Images.Media.DISPLAY_NAME,
        MediaStore.Images.Media.SIZE,
        MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
        MediaStore.Images.Media.BUCKET_ID,
        MediaStore.Images.Media._ID,
        MediaStore.Images.Media.DATE_TAKEN
    )

    @SuppressLint("InlinedApi")
    fun getAllPictureContents(contentLocation: Uri?): ArrayList<PhotoModel> {
        val images = ArrayList<PhotoModel>()
        cursor = pictureContex.contentResolver.query(
            contentLocation!!, projections, null, null,
            "LOWER (" + MediaStore.Images.Media.DATE_TAKEN + ") DESC"
        )
        try {
            cursor!!.moveToFirst()
            do {

                val pictureContent = PhotoModel()

                val pictureName = cursor!!.getString(
                    cursor!!.getColumnIndexOrThrow(
                        MediaStore.Images.Media.DISPLAY_NAME
                    )
                )

                pictureContent.pictureName = pictureName

                val picturePath = cursor!!.getString(
                    cursor!!.getColumnIndexOrThrow(
                        MediaStore.Images.Media.DATA
                    )
                )

                pictureContent.picturePath = picturePath

                val id =
                    cursor!!.getInt(cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media._ID))
                pictureContent.pictureId = id
                val contentUri = Uri.withAppendedPath(contentLocation, id.toString())
                pictureContent.assertFileUri = contentUri.toString()
                images.add(pictureContent)
            } while (cursor!!.moveToNext())
            cursor!!.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return images
    }

    @get:SuppressLint("InlinedApi")
    val allFolders: ArrayList<PictureFolder>
        get() {
            val absolutePictureFolders = ArrayList<PictureFolder>()
            val picturePaths = ArrayList<Int>()
            cursor = pictureContex.contentResolver.query(
                externalContentUri, projections, null, null,
                "LOWER (" + MediaStore.Images.Media.DATE_TAKEN + ") DESC"
            )
            try {
                cursor!!.moveToFirst()
                do {
                    val photoFolder = PictureFolder()
                    val pictureContent = PhotoModel()

                    pictureContent.pictureName =
                        cursor!!.getString(
                            cursor!!.getColumnIndexOrThrow(
                                MediaStore.Images.Media.DISPLAY_NAME
                            )
                        )

                    pictureContent.picturePath =
                        cursor!!.getString(
                            cursor!!.getColumnIndexOrThrow(
                                MediaStore.Images.Media.DATA
                            )
                        )

                    val id =
                        cursor!!.getInt(cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media._ID))
                    pictureContent.pictureId = id
                    pictureContent.assertFileUri =
                        Uri.withAppendedPath(externalContentUri, id.toString()).toString()

                    val folder =
                        cursor!!.getString(cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME))
                    val datapath =
                        cursor!!.getString(cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                    val bucket_id =
                        cursor!!.getInt(cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_ID))
                    var folderpaths = datapath.substring(0, datapath.lastIndexOf("$folder/"))
                    folderpaths = "$folderpaths$folder/"
                    if (!picturePaths.contains(bucket_id)) {
                        picturePaths.add(bucket_id)
                        photoFolder.bucket_id = bucket_id
                        photoFolder.folderPath = folderpaths
                        photoFolder.folderName = folder
                        photoFolder.photos?.add(pictureContent)
                        absolutePictureFolders.add(photoFolder)
                    } else {
                        for (folderX in absolutePictureFolders) {
                            if (folderX.bucket_id == bucket_id) {
                                folderX.photos?.add(pictureContent)
                            }
                        }
                    }
                } while (cursor!!.moveToNext())
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return absolutePictureFolders
        }

    companion object {
        private var pictureGet: FetchPicture? = null
        val externalContentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val internalContentUri = MediaStore.Images.Media.INTERNAL_CONTENT_URI
        private var cursor: Cursor? = null

        @JvmStatic
        fun getInstance(context: Context): FetchPicture? {
            if (pictureGet == null) {
                pictureGet = FetchPicture(context)
            }
            return pictureGet
        }
    }
}
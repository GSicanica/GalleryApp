package com.jabukaprog.family.repository

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jabukaprog.family.data.PhotoModel
import com.jabukaprog.family.data.fetcher.picture.Fetcher
import com.jabukaprog.family.data.fetcher.picture.FetchPicture
import com.jabukaprog.family.data.fetcher.picture.PictureFolder
import javax.inject.Inject
import kotlin.math.ceil


class PhotosPagingRepository @Inject constructor(private val context: Context) {

    private val allPhotos = Fetcher
        .withPictureContext(context)
        ?.getAllPictureContents(FetchPicture.externalContentUri)

    var pictureFolders = arrayListOf<PictureFolder>()


    init {
        pictureFolders.addAll(Fetcher.withPictureContext(context)!!.allFolders)
    }

    val dataBatchSize = 25

    class DesiredLoadResultPageResponse(
        val data: List<PhotoModel>
    )

    fun searchItemsByKeyOne(key: Int): DesiredLoadResultPageResponse {
        val maxKey = ceil(allPhotos!!.size.toFloat() / dataBatchSize).toInt()
        if (key >= maxKey) {
            return DesiredLoadResultPageResponse(emptyList())
        }
        val from = key * dataBatchSize
        val to = minOf((key + 1) * dataBatchSize, allPhotos.size)
        val currentSublist = allPhotos.subList(from, to)
        return DesiredLoadResultPageResponse(currentSublist)
    }


    fun getPhotosOne(): PagingSource<Int, PhotoModel> {
        return object : PagingSource<Int, PhotoModel>() {
            override fun getRefreshKey(state: PagingState<Int, PhotoModel>): Int? {
                return state.anchorPosition?.let {
                    state.closestPageToPosition(it)?.prevKey?.plus(1)
                        ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
                }
            }
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoModel> {
                val pageNumber = params.key ?: 0
                val response = searchItemsByKeyOne(pageNumber)
                val prevKey = if (pageNumber > 0) pageNumber - 1 else null
                val nextKey = if (response.data.isNotEmpty()) pageNumber + 1 else null
                return LoadResult.Page(
                    data = response.data,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            }
        }
    }

}
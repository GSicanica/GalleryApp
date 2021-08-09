package com.jabukaprog.family.ui.compose.photogird

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jabukaprog.family.BaseViewModel
import com.jabukaprog.family.data.PhotoModel
import com.jabukaprog.family.data.databases.PhotoModelEntity
import com.jabukaprog.family.data.fetcher.picture.PictureFolder
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotoGridScreen(
    viewModel: BaseViewModel,
    lastWord: String?,
    pictureFolders: ArrayList<PictureFolder>
) {
    var allPhotos = arrayListOf<PhotoModel>()
    var myValue by remember { mutableStateOf(false) }

    for (i in pictureFolders.indices) {
        val output =
            pictureFolders[i].folderPath!!.substring(0, pictureFolders[i].folderPath!!.length - 1)
        val folderName = output.substring(output.lastIndexOf("/") + 1)
        if (folderName == lastWord) {
            allPhotos = pictureFolders[i].photos!!
        }
    }

    @Composable
    fun LottiePlayOnce() {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(com.android.family.R.raw.heart_like))
        LottieAnimation(composition, modifier = Modifier.size(100.dp))
    }

    if (myValue) {
        LottiePlayOnce()
    }

    LazyVerticalGrid(GridCells.Adaptive(100.dp)) {
        itemsIndexed(allPhotos) { index, item ->
            Card(
                Modifier
                    .padding(4.dp)
                    .combinedClickable(
                        onClick = {},
                        onLongClick = {},
                        onDoubleClick = {}
                    )
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                val num =
                                    item.assertFileUri!!
                                        .substring(item.assertFileUri!!.lastIndexOf("/") + 1)
                                        .toLong()
                                val photoModelDB =
                                    PhotoModelEntity(path = item.assertFileUri!!, id = num)
                                viewModel.insertPhotoModelDB(photoModelDB)
                                viewModel.saveSharedPref(item.assertFileUri!!)
                                myValue = !myValue
                            }
                        )
                    }
            ) {
                CoilImage(
                    imageModel = item.assertFileUri!!,
                    modifier = Modifier.size(100.dp),
                    shimmerParams = ShimmerParams(
                        baseColor = MaterialTheme.colors.background,
                        highlightColor = Color.White,
                        durationMillis = 50,
                        dropOff = 0.65f,
                        tilt = 20f
                    ),
                    failure = { Icons.Default.Error }
                )
            }

            Spacer(modifier = Modifier.padding(start = 110.dp))
        }
    }
}

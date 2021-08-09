package com.jabukaprog.family.ui.compose.folder

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jabukaprog.family.data.fetcher.picture.PictureFolder
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage
import timber.log.Timber


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FolderScreen(
    pictureFolders: ArrayList<PictureFolder>,
    navController: NavHostController
) {

    LazyVerticalGrid(GridCells.Adaptive(100.dp)) {
        itemsIndexed(pictureFolders) { _, item ->
            Card(
                Modifier
                    .padding(4.dp)
                    .combinedClickable(
                        onClick = {
                            Timber.d(item.folderPath!!)
                            val path = item.folderPath!!.substring(0, item.folderPath!!.length - 1)
                            val folderName = path.substring(path.lastIndexOf("/") + 1)
                            navController.navigate("photoGridScreen/$folderName")
                        },
                        onLongClick = {},
                        onDoubleClick = {}
                    )
            ) {
                CoilImage(
                    imageModel = item.photos?.get(0)?.assertFileUri!!,
                    modifier = Modifier.size(100.dp),
                    shimmerParams = ShimmerParams(
                        baseColor = MaterialTheme.colors.background,
                        highlightColor = Color.White,
                        durationMillis = 50,
                        dropOff = 0.65f,
                        tilt = 20f
                    ),
                    failure = {
                        Icons.Default.Error
                    }
                )
            }

            Spacer(modifier = Modifier.padding(start = 110.dp))

            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color.Red),
                textAlign = TextAlign.Center,
                color = Color.White,
                text = item.folderName!!
            )

        }
    }
}
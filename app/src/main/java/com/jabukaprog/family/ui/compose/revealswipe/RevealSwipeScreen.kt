package com.jabukaprog.family.ui.compose.revealswipe

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.jabukaprog.family.data.PhotoModel
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage
import kotlinx.coroutines.launch


@Composable
fun RevealSwipeScreen(lazyPagingItems: LazyPagingItems<PhotoModel>) {

    val composableScope = rememberCoroutineScope()

    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .padding(top = 2.dp)
    ) {
        itemsIndexed(lazyPagingItems) { index, item ->
            RevealSwipeExample(item)
        }
        composableScope.launch {
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RevealSwipeExample(
    photoModel: PhotoModel?
) {
    RevealSwipe(
        modifier = Modifier.padding(vertical = 5.dp).width(200.dp),
        directions = setOf(
            RevealDirection.StartToEnd,
            RevealDirection.EndToStart
        ),
        hiddenContentStart = {
            Icon(
                modifier = Modifier.padding(horizontal = 25.dp),
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = Color.White
            )
        },
        hiddenContentEnd = {
            Icon(
                modifier = Modifier.padding(horizontal = 25.dp),
                imageVector = Icons.Outlined.Delete,
                contentDescription = null
            )
        }
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .requiredHeight(80.dp),
            shape = it,
        ) {
            CoilImage(
                imageModel = photoModel?.assertFileUri!!,
                modifier = Modifier.size(100.dp),
                shimmerParams = ShimmerParams(
                    baseColor = MaterialTheme.colors.background,
                    highlightColor = Color.White,
                    durationMillis = 50,
                    dropOff = 0.65f,
                    tilt = 20f
                ),
                failure = {
                    Text(text = "image request failed.")
                }
            )
        }
    }
}
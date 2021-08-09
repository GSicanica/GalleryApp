package com.jabukaprog.family.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import com.android.family.R
import com.jabukaprog.family.BaseViewModel
import com.jabukaprog.family.MainNavRoutes
import com.jabukaprog.family.data.PhotoModel
import com.jabukaprog.family.data.fetcher.picture.PictureFolder
import com.jabukaprog.family.ui.compose.SpeedDialData
import com.jabukaprog.family.ui.compose.SpeedDialFloatingActionButton
import com.jabukaprog.family.ui.compose.TopAppBarFamily
import com.jabukaprog.family.ui.compose.drawer.NavDrawer
import com.jabukaprog.family.ui.compose.theme.softGray


@Composable
fun MainScreen(
    lazyPagingItems: LazyPagingItems<PhotoModel>,
    pictureFolders: ArrayList<PictureFolder>,
    viewModel: BaseViewModel,
    navController: NavHostController,
    onToggleTheme: () -> Unit
) {
    val stateDrawer = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = stateDrawer,
        drawerShape = RoundedCornerShape(topEnd = 23.dp, bottomEnd = 23.dp),
        drawerContent = { NavDrawer(stateDrawer, coroutineScope) },
        drawerBackgroundColor = softGray,
        content = {
            Surface(
                color = softGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Column() {
                    TopAppBarFamily(
                        onToggleTheme, lazyPagingItems, pictureFolders,
                        stateDrawer, coroutineScope, viewModel, navController
                    )
                }

                Column(
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.padding(end = 6.dp, bottom = 8.dp)
                )
                {
                    SpeedDialFloatingActionButton(
                        onClick = {
                            when (it?.name) {
                                "Camera" -> {
                                    navController.navigate(MainNavRoutes.CameraScreen)
                                }
                                "gallery" -> {
                                    navController.navigate(MainNavRoutes.RevealSwipeScreen)
                                }
                                "calendar" -> {
                                    navController.navigate(MainNavRoutes.CalendarScreen)
                                }
                                "PickImage" -> {
                                    navController.navigate(MainNavRoutes.ImageFetcherFromGalleryScreen)
                                }
                            }
                        },
                        speedDialData = listOf(
                            SpeedDialData(
                                name = "Camera",
                                painter = painterResource(id = R.drawable.ic_baseline_photo_camera_24)
                            ),
                            SpeedDialData(
                                name = "gallery",
                                painter = painterResource(R.drawable.ic_baseline_image_24)
                            ),
                            SpeedDialData(
                                name = "calendar",
                                painter = painterResource(id = R.drawable.ic_baseline_calendar_today_24)
                            ),
                            SpeedDialData(
                                name = "PickImage",
                                painter = painterResource(id = R.drawable.ic_baseline_add_comment_24)
                            )
                        )
                    )
                }
            }
        })
}


package com.jabukaprog.family.ui.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import com.android.family.R
import com.jabukaprog.family.BaseViewModel
import com.jabukaprog.family.data.PhotoModel
import com.jabukaprog.family.data.fetcher.picture.PictureFolder
import com.jabukaprog.family.ui.compose.folder.FolderScreen
import com.jabukaprog.family.ui.compose.photogird.FavoritePictureScreen
import com.jabukaprog.family.ui.compose.revealswipe.RevealSwipeScreen
import com.jabukaprog.family.ui.compose.theme.iconsBackground
import com.jabukaprog.family.ui.compose.theme.purple500
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun TopAppBarFamily(
    onToggleTheme: () -> Unit,
    lazyPagingItems: LazyPagingItems<PhotoModel>,
    pictureFolders: ArrayList<PictureFolder>,
    state: ScaffoldState,
    coroutineScope: CoroutineScope,
    viewModel: BaseViewModel,
    navController: NavHostController,
) {
    Surface(
        color = Color.White
    ) {

        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .padding(start = 24.dp, end = 8.dp, top = 8.dp)
                .fillMaxWidth()
                .size(40.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.fam),
                contentDescription = "null",
            )

            Spacer(modifier = Modifier.padding(start = 80.dp))

            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(iconsBackground)
            ) {
                Image(
                    painter = painterResource(R.drawable.lupa),
                    contentDescription = ""
                )
            }

            Spacer(modifier = Modifier.padding(end = 16.dp))

            IconButton(
                onClick = {
                    onToggleTheme
                    //navController.navigate(route = ChatRoutes.Contacts)

                },
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(iconsBackground)
            ) {
                Image(
                    painter = painterResource(R.drawable.chat),
                    contentDescription = ""
                )
            }
        }
    }


    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    )
    {
        TabsLayout(
            lazyPagingItems,
            pictureFolders,
            state,
            coroutineScope,
            viewModel,
            navController
        )
    }

}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun TabsLayout(
    lazyPagingItems: LazyPagingItems<PhotoModel>,
    pictureFolders: ArrayList<PictureFolder>,
    state: ScaffoldState,
    coroutineScope: CoroutineScope,
    viewModel: BaseViewModel,
    navController: NavHostController,
) {
    val selectedTabIndex = remember { mutableStateOf(0) }

    val indicator = @Composable { tabPositions: List<TabPosition> ->
        val modifier = Modifier.tabIndicatorOffset(
            tabPositions[selectedTabIndex.value]
        )
        Box(
            modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(
                    color = Color.Red,
                    shape = RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomEnd = 16.dp,
                        bottomStart = 16.dp
                    )
                )
        )
    }

    TabRow(
        selectedTabIndex = selectedTabIndex.value,
        indicator = indicator,
        backgroundColor = Color.White
    ) {
        Tab(
            selected = false,
            onClick = { selectedTabIndex.value = 0 },
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_baseline_home_24),
                contentDescription = "",
                modifier = Modifier.padding(16.dp),
                tint = if (selectedTabIndex.value == 0) purple500 else Color.Gray,
            )
        }

        /*Tab(
            selected = false,
            onClick = { selectedTabIndex.value = 1 },
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_round_groups_24),
                modifier = Modifier.padding(16.dp),
                contentDescription = "Default Icon Toggle Button",
                tint = if (selectedTabIndex.value == 1) purple500 else Color.Gray
            )
        }*/

        Tab(selected = false, onClick = { selectedTabIndex.value = 1 }) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = "",
                modifier = Modifier.padding(16.dp),
                tint = if (selectedTabIndex.value == 2) purple500 else Color.Gray,
            )
        }

        Tab(selected = false, onClick = {
            coroutineScope.launch { state.drawerState.open() }
            selectedTabIndex.value = 2
        })
        {
            Icon(
                painter = painterResource(R.drawable.ic_round_menu_24), contentDescription = "",
                modifier = Modifier.padding(16.dp),
                tint = if (selectedTabIndex.value == 3) purple500 else Color.Gray,
            )
        }
    }

    when (selectedTabIndex.value) {
        0 -> FolderScreen(
            pictureFolders,
            navController
        )
        //1 -> RevealSwipeScreen(lazyPagingItems)
        1 -> FavoritePictureScreen(viewModel)
        else -> println("I don't know anything about it")
    }
}

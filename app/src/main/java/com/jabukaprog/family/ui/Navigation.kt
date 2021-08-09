package com.jabukaprog.family.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import androidx.paging.Pager
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import com.jabukaprog.family.BaseViewModel
import com.jabukaprog.family.ChatRoutes
import com.jabukaprog.family.MainNavRoutes
import com.jabukaprog.family.data.PhotoModel
import com.jabukaprog.family.data.fetcher.picture.PictureFolder
import com.jabukaprog.family.data.protodata.ProtoManager
import com.jabukaprog.family.ui.compose.calendar.CalendarPreviewScreen
import com.jabukaprog.family.ui.compose.camera.LauncherForActivityResultScreen
import com.jabukaprog.family.ui.compose.chat.ui.Contacts
import com.jabukaprog.family.ui.compose.chat.ui.Messages
import com.jabukaprog.family.ui.compose.pickImage.PickImageScreen
import com.jabukaprog.family.ui.compose.folder.FolderScreen
import com.jabukaprog.family.ui.compose.photogird.PhotoGridScreen
import com.jabukaprog.family.ui.compose.revealswipe.RevealSwipeScreen
import kotlinx.coroutines.CoroutineScope
import timber.log.Timber


@Composable
fun Navigation(
    pager: Pager<Int, PhotoModel>,
    pictureFolders: ArrayList<PictureFolder>,
    composableScope: CoroutineScope,
    viewModel: BaseViewModel,
    firebaseAnalytics: FirebaseAnalytics,
    protoManager: ProtoManager,
    onToggleTheme: () -> Unit
) {

    val lazyPagingItems = pager.flow.collectAsLazyPagingItems()
    val navController = rememberNavController()

    ProvideWindowInsets {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            destination.route?.let { route ->
                Timber.d("Route : $route")
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
                    param(FirebaseAnalytics.Param.SCREEN_NAME, route)
                }
            }
        }

        NavHost(
            navController = navController,
            startDestination = MainNavRoutes.MainScreen
        ) {

            composable(route = MainNavRoutes.MainScreen) {
                MainScreen(lazyPagingItems, pictureFolders, viewModel, navController, onToggleTheme)
            }

            composable(route = MainNavRoutes.RevealSwipeScreen) {
                RevealSwipeScreen(lazyPagingItems)
            }

            composable(route = MainNavRoutes.CameraScreen) { LauncherForActivityResultScreen() }

            composable(route = MainNavRoutes.ImageFetcherFromGalleryScreen,
                content = { PickImageScreen() })

            composable(route = MainNavRoutes.CalendarScreen,
                content = { CalendarPreviewScreen() })

            composable(route = ChatRoutes.ContactsScreen,
                content = { Contacts(navController) })

            composable(route = ChatRoutes.MessagesScreen, content = { Messages() })

            composable(route = MainNavRoutes.PhotoGridScreen) {
                PhotoGridScreen(viewModel, "", pictureFolders)
            }

            composable("photoGridScreen/{folderName}",
                arguments = listOf(
                    navArgument("folderName") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                backStackEntry.arguments?.getString("folderName")?.let { path ->
                    PhotoGridScreen(viewModel, path, pictureFolders)
                }
            }

            composable(route = MainNavRoutes.FolderScreen) {
                FolderScreen(pictureFolders, navController)
            }
        }
    }
}
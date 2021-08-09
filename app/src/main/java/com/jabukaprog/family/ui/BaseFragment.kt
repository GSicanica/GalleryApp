package com.jabukaprog.family.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.google.firebase.analytics.FirebaseAnalytics
import com.jabukaprog.family.BaseApplication
import com.jabukaprog.family.BaseViewModel
import com.jabukaprog.family.data.PhotoModel
import com.jabukaprog.family.data.fetcher.picture.PictureFolder
import com.jabukaprog.family.data.protodata.ProtoManager
import com.jabukaprog.family.repository.PhotosPagingRepository
import com.jabukaprog.family.ui.compose.theme.FamilyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

@AndroidEntryPoint
class BaseFragment() : Fragment() {

    @Inject
    lateinit var firebaseAnalytics: FirebaseAnalytics

    @Inject
    lateinit var protoManager: ProtoManager

    @Inject
    lateinit var photosPagingRepository: PhotosPagingRepository

    @Inject
    lateinit var application: BaseApplication

    private val viewModel: BaseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val myBackend = remember { PhotosPagingRepository(requireContext()) }
                val pagerOne = remember {
                    Pager(
                        PagingConfig(
                            pageSize = photosPagingRepository.dataBatchSize,
                            enablePlaceholders = true,
                            maxSize = 85000
                        )
                    ) {
                        myBackend.getPhotosOne()
                    }
                }
                val pictureFolders = PhotosPagingRepository(requireContext()).pictureFolders
                val composableScope = rememberCoroutineScope()
                val context = LocalContext.current
                BaseCompose(
                    pager = pagerOne,
                    pictureFolders = pictureFolders,
                    composableScope = composableScope
                )
            }
        }
    }

    @Composable
    fun BaseCompose(
        pager: Pager<Int, PhotoModel>,
        pictureFolders: ArrayList<PictureFolder>,
        composableScope: CoroutineScope
    ) {
        val scaffoldState = rememberScaffoldState()
        FamilyTheme(
            scaffoldState = scaffoldState,
            darkTheme = application.isDark.value,
        ) {
            Navigation(
                pager, pictureFolders, composableScope,
                viewModel, firebaseAnalytics, protoManager, application::toggleLightTheme
            )
        }
    }
}
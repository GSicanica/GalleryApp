package com.jabukaprog.family


import android.app.Application
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.jabukaprog.family.data.protodata.ProtoManager
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class BaseApplication : Application() {

    private val scope = CoroutineScope(Dispatchers.Main)

    @Inject
    lateinit var protoManager: ProtoManager

    @Inject
    lateinit var firebaseAnalytics: FirebaseAnalytics

    @Inject
    lateinit var firebaseCrashlytics: FirebaseCrashlytics

    val isDark = mutableStateOf(false)

    fun toggleLightTheme(){
        isDark.value = !isDark.value
    }

    override fun onCreate() {
        super.onCreate()
        // throw RuntimeException("Test Crash") // Force a crash

        scope.launch {
            protoManager.isAnalyticsEnabled.collect { isEnabled ->
                Timber.d("Analytics collection: $isEnabled")
                firebaseAnalytics.setAnalyticsCollectionEnabled(true)
            }
        }
        scope.launch {
            protoManager.isCrashlyticsEnabled.collect { isEnabled ->
                Timber.d("Crashlytics collection: $isEnabled")
                firebaseCrashlytics.setCrashlyticsCollectionEnabled(true)
            }
        }
    }
}
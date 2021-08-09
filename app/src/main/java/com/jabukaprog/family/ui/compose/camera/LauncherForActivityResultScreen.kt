package com.jabukaprog.family.ui.compose.camera

import android.Manifest
import android.graphics.Bitmap
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.jabukaprog.family.utils.isCameraPermissionGranted

private const val Url = "activity/LauncherForActivityResultScreen.kt"

@Composable
fun LauncherForActivityResultScreen() {
    LauncherForActivityResultExample()
}

@Composable
private fun LauncherForActivityResultExample() {
    val context = LocalContext.current
    val result = remember { mutableStateOf<Bitmap?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {
        result.value = it
    }
    val permissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                launcher.launch()
            } else {
                Toast.makeText(context, "Permission not granted", Toast.LENGTH_SHORT).show()
            }
        }

    Button(
        onClick = {
            if (context.isCameraPermissionGranted) {
                launcher.launch()
            } else {
                permissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    ) {
        Text(text = "Take a picture")
    }

    result.value?.let { image ->
        Image(
            image.asImageBitmap(),
            null,
            modifier = Modifier
                .size(250.dp)
        )
    }
}
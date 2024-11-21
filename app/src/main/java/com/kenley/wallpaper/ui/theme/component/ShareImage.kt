package com.kenley.wallpaper.ui.theme.component

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

@Composable
fun ShareImageByUrlComponent(activity: ComponentActivity, imageUrl: String) {
//    val shareIntent: Intent = Intent().apply {
//        action = Intent.ACTION_SEND
//        // Example: content://com.google.android.apps.photos.contentprovider/...
//        putExtra(Intent.EXTRA_STREAM, "")
//        type = "image/jpeg"
//    }
//    startActivity(Intent.createChooser(shareIntent, null))
}
package com.kenley.wallpaper.ui.theme.component

import android.content.Intent
import android.graphics.Bitmap
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.kenley.wallpaper.R

@Composable
fun ImagePopup(imageRes: String, onDismiss: () -> Unit) {
    val context = LocalContext.current
    val shareImage = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {}
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageRes)
            .size(coil.size.Size.ORIGINAL) // Set the target size to load the image at.
            .build()
    )
    val imageState = painter.state
    var bitmap = remember<Bitmap?> {
        null
    }
    if (imageState is AsyncImagePainter.State.Success) {
        bitmap = imageState.result.drawable.toBitmap()
    }
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .background(color = Color.Black.copy(alpha = 0.5f))
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = imageRes,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(400.dp).clip(shape = RoundedCornerShape(10.dp)))
                
                Row(modifier = Modifier.fillMaxWidth().padding(top = 20.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Box(modifier = Modifier.border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp)).clip(shape = RoundedCornerShape(10.dp)).background(Color(0x80808080)).size(50.dp).clickable {
                        onDismiss()
                    }, contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "",
                            tint = Color.White)
                    }
                    Box(modifier = Modifier.border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp)).clip(shape = RoundedCornerShape(10.dp)).background(Color(0x80808080)).size(50.dp).clickable {
                        bitmap?.let {
                            saveImageToDevice(it, context)
                        }
                    }, contentAlignment = Alignment.Center) {
                        Image(
                            painter = painterResource(R.drawable.download), // Replace with your image resource
                            contentDescription = null)
                    }
                    Box(modifier = Modifier.border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp)).clip(shape = RoundedCornerShape(10.dp)).background(Color(0x80808080)).size(50.dp).clickable {
                        val shareIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_STREAM, imageRes)
                            type = "image/jpeg"
                        }

                        shareImage.launch(Intent.createChooser(shareIntent, null))
                    }, contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "",
                            tint = Color.White)
                    }
                }
            }
        }
    }
}
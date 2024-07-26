package com.kenley.wallpaper.ui.theme.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.kenley.wallpaper.R

@Composable
fun ImagePopup(imageRes: String, onDismiss: () -> Unit) {
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

                    }, contentAlignment = Alignment.Center) {
                        Image(
                            painter = painterResource(R.drawable.download), // Replace with your image resource
                            contentDescription = null)
                    }
                    Box(modifier = Modifier.border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp)).clip(shape = RoundedCornerShape(10.dp)).background(Color(0x80808080)).size(50.dp).clickable {
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
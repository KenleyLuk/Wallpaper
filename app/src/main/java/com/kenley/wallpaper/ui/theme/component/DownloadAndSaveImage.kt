package com.kenley.wallpaper.ui.theme.component

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.OutputStream

fun saveImageToDevice(bitmap: Bitmap, context: Context) {
    GlobalScope.launch(Dispatchers.IO) {
        val contentResolver: ContentResolver = context.contentResolver
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "image.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        }

        val imageUri: Uri? = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        imageUri?.let { uri ->
            try {
                contentResolver.openOutputStream(uri)?.use { outputStream: OutputStream ->
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                    outputStream.flush()
                    showToast(context, "Image saved to album")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                showToast(context, "Failed to save image")
            }
        }
    }
}

private fun showToast(context: Context, message: String) {
    GlobalScope.launch(Dispatchers.Main) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
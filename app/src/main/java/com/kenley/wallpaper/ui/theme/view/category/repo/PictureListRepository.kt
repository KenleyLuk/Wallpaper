package com.kenley.wallpaper.ui.theme.view.category.repo

import android.util.Log
import com.kenley.wallpaper.services.ApiService
import com.kenley.wallpaper.apiData.ImageData
import java.lang.Exception
import com.kenley.wallpaper.API_KEY

interface PictureListRepository {
    suspend fun getImage(query: String, orientation: String, order: String, type: String, colors: String): List<ImageData>
}

class PictureListRepositoryImpl(private val apiService: ApiService) : PictureListRepository {
    override suspend fun getImage(query: String, orientation: String, order: String, type: String, colors: String): List<ImageData> {
        return try {
            apiService.getImages(
                apiKey = API_KEY, query = query, imageType = type, orientation = orientation, order = order, colors = colors, pretty = true).hits
        } catch (e: Exception) {
            // Handle the ConnectException
            // For example, log the error, display an error message, or return an empty list
            Log.d("UserRepositoryImpl", e.toString())
            e.printStackTrace()
            emptyList()
        }
    }
}
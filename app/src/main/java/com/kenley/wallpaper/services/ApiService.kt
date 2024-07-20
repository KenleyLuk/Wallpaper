package com.kenley.wallpaper.services

import com.kenley.wallpaper.BASE_URL
import com.kenley.wallpaper.apiData.ImageObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/")
    suspend fun getImages(
        @Query("key") apiKey: String,
        @Query("q") query: String,
        @Query("image_type") imageType: String,
        @Query("orientation") orientation: String,
        @Query("order") order: String,
        @Query("colors") colors: String,
        @Query("pretty") pretty: Boolean
    ): ImageObject
}


object RetrofitClient {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
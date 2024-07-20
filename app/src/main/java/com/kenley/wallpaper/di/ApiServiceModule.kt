package com.kenley.wallpaper.di

import com.kenley.wallpaper.services.ApiService
import com.kenley.wallpaper.services.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {
    @Provides
    fun provideApiService(): ApiService {
        return RetrofitClient.apiService
    }
}
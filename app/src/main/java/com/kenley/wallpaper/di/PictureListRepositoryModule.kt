package com.kenley.wallpaper.di

import com.kenley.wallpaper.services.ApiService
import com.kenley.wallpaper.ui.theme.view.category.repo.PictureListRepository
import com.kenley.wallpaper.ui.theme.view.category.repo.PictureListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PictureListRepositoryModule {

    @Provides
    fun provideUserRepository(apiService: ApiService): PictureListRepository {
        return PictureListRepositoryImpl(apiService)
    }
}
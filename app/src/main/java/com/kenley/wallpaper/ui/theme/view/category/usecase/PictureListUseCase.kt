package com.kenley.wallpaper.ui.theme.view.category.usecase

import com.kenley.wallpaper.apiData.ImageData
import com.kenley.wallpaper.ui.theme.view.category.repo.PictureListRepository

class PictureListUseCase(private val pictureListRepository: PictureListRepository) {
    suspend operator fun invoke(query: String, orientation: String, order: String, type: String, colors: String): List<ImageData>{
        return pictureListRepository.getImage(query, orientation, order, type, colors)
    }
}
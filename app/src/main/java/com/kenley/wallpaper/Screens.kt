package com.kenley.wallpaper

sealed class Screens(val route: String) {
    object LandingScreen: Screens("Landing")
    object PictureListScreen: Screens("PictureList")

    object PictureDetailScreen: Screens("PictureDetailScreen")
}
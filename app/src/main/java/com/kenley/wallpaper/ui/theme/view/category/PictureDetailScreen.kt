package com.kenley.wallpaper.ui.theme.view.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kenley.wallpaper.ui.theme.view.category.viewmodel.PictureListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PictureDetailScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)) {

    }
}
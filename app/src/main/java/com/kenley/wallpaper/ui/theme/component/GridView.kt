package com.kenley.wallpaper.ui.theme.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kenley.wallpaper.model.ColorPalette

@Composable
fun GridView(gridItems: List<ColorPalette>, numColumns: Int, selectedItem: String, modifier: Modifier, onItemClick: (ColorPalette) -> Unit) {
    LazyVerticalGrid(
        modifier = modifier.padding(top = 10.dp),
        columns = GridCells.Fixed(6)) {
        items(gridItems) { item ->
            GridItem(item = item, selectedItem = selectedItem, onItemClick = onItemClick)
        }
    }
}

@Composable
fun GridItem(item: ColorPalette, selectedItem: String, onItemClick: (ColorPalette) -> Unit) {
    Box(
        modifier = Modifier
            .padding(end = 20.dp, bottom = 20.dp)
            .width(60.dp)
            .height(35.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(
                width = 1.dp,
                color = if (selectedItem == item.name) Color(android.graphics.Color.parseColor(ColorPalette.getColorResource(item))) else Color.Transparent,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable { onItemClick(item) }
    ) {
        Box(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))
                .background(Color(android.graphics.Color.parseColor(ColorPalette.getColorResource(item))))
                .clickable { onItemClick(item) }
        )
    }
}
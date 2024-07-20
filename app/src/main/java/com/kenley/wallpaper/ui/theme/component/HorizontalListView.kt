package com.kenley.wallpaper.ui.theme.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalListView(
    items: List<String>,
    modifier: Modifier,
    onItemClick: (String) -> Unit,
    selectedItem: String
) {
    LazyRow(modifier = modifier) {
        items(items) { item ->
            Box(modifier = Modifier
                .padding(end = 10.dp)
                .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .background(
                    if (selectedItem.isNotEmpty() && selectedItem == item) {
                        Color(0XFF404040)
                    } else {
                        Color.White
                    })
                .clickable {
                    if (item == selectedItem) {
                        onItemClick("")
                    } else {
                        onItemClick(item)
                    }
                }) {
                Text(text = item, color = if (selectedItem.isNotEmpty() && selectedItem == item) {
                    Color.White
                } else {
                    Color(0xFF404040)
                }, modifier = Modifier.padding(start = 10.dp, top = 6.dp, end = 10.dp, bottom = 6.dp))
            }
        }
    }
}
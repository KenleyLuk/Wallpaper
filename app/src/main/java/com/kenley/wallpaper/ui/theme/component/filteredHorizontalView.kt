package com.kenley.wallpaper.ui.theme.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kenley.wallpaper.model.ColorPalette
import com.kenley.wallpaper.model.FilteredItem
import com.kenley.wallpaper.model.FilteredType

@Composable
fun FilteredHorizontalView(items: List<FilteredItem>, selectedItem: String, onCloseClick: (FilteredType) -> Unit) {
    LazyRow {
        items(items) { item ->
            Box(modifier = Modifier
                .padding(start = 10.dp, end = 10.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0XFFE5E4E2))
                .clickable {
                    onCloseClick(item.type)
                }) {

                if (item.type != FilteredType.Color) {
                    Row {
                        Text(
                                    text = item.name, color = Color.Black
                            , modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp))
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "",
                            tint = Color(0XFF808080),
                            modifier = Modifier.padding(start = 4.dp, top = 8.dp, bottom = 8.dp, end = 8.dp))
                    }
                } else {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.padding(start = 16.dp, top = 10.dp, bottom = 10.dp).width(25.dp).height(20.dp).clip(RoundedCornerShape(5.dp)).background(Color(android.graphics.Color.parseColor(ColorPalette.getColorResource(ColorPalette.myValueOf(item.name))))))
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "",
                            tint = Color(0XFF808080),
                            modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 8.dp, end = 8.dp))
                    }
                }

            }
        }
    }
}
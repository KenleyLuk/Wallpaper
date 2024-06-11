package com.kenley.wallpaper.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.kenley.wallpaper.R


@Composable
fun PictureListScreen() {
    val colorBeluga = colorResource(id = R.color.BELUGA)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorBeluga)
    ) {
        Column() {
            Row(horizontalArrangement = Arrangement.SpaceAround) {
                Text(
                    text = "Category",
                    style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold),
                    color = Color.Black,
                    modifier = Modifier
                        .padding(start = 20.dp, top = 20.dp))
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .background(colorBeluga)
            ) {
                CustomSearchView(search = "", modifier = Modifier, onValueChange = {})
            }

            val item = listOf("backgrounds", "fashion", "nature", "science", "education", "feelings", "health", "people", "religion", "places", "animals", "industry", "computer", "food", "sports", "transportation", "travel", "buildings", "business", "music")
            Box(modifier = Modifier.padding(start = 10.dp, top = 10.dp)) {
                HorizontalRecyclerView(item)
            }
            
            Box(modifier = Modifier.padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 20.dp)) {
                StaggeredGridRecyclerView(photos = item)
            }
        }
    }
}

@Composable
fun HorizontalRecyclerView(items: List<String>) {
    LazyRow {
        items(items) { item ->
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
            ) {
                Text(text = item, modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@Composable
fun StaggeredGridRecyclerView(photos: List<String>) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 10.dp,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        content = {
            items(photos.size) { photo ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)
                ) {
                    Image(painter = painterResource(id = R.drawable.welocme), contentDescription = null)
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

@Preview
@Composable
fun PreviewPictureListScreenLayout() {
    val navController = rememberNavController()
    PictureListScreen()
}
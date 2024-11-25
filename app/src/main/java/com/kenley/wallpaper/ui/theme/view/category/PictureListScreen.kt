package com.kenley.wallpaper.ui.theme.view.category

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.kenley.wallpaper.R
import com.kenley.wallpaper.Screens
import com.kenley.wallpaper.apiData.ImageData
import com.kenley.wallpaper.ui.theme.view.category.viewmodel.PictureListViewModel
import com.kenley.wallpaper.model.ColorPalette
import com.kenley.wallpaper.model.FilteredItem
import com.kenley.wallpaper.model.FilteredType
import com.kenley.wallpaper.ui.theme.component.CustomSearchView
import com.kenley.wallpaper.ui.theme.component.FilteredHorizontalView
import com.kenley.wallpaper.ui.theme.component.GridView
import com.kenley.wallpaper.ui.theme.component.HorizontalListView
import com.kenley.wallpaper.ui.theme.component.ImagePopup
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PictureListScreen(navController: NavHostController, viewModel: PictureListViewModel = hiltViewModel()) {
    val tags = listOf(
        "backgrounds",
        "fashion",
        "nature",
        "science",
        "education",
        "feelings",
        "health",
        "people",
        "religion",
        "places",
        "animals",
        "industry",
        "computer",
        "food",
        "sports",
        "transportation",
        "travel",
        "buildings",
        "business",
        "music")

    val orderTags = listOf(
        "Popular", "Latest")
    val orientationTags = listOf(
        "Horizontal", "Vertical")
    val typeTags = listOf(
        "Photo", "Illustration", "Vector")
    val colorBeluga = colorResource(id = R.color.BELUGA)
    val result by viewModel.image.observeAsState()
    var searchStr by remember { mutableStateOf("") }
    var selectedItemStr by remember { mutableStateOf("") }
    var selectedOrderStr by remember { mutableStateOf("") }
    var selectedOrientationStr by remember { mutableStateOf("") }
    var selectedTypeStr by remember { mutableStateOf("") }
    var selectedColorStr by remember { mutableStateOf("") }
    val filteredItemList = mutableListOf<FilteredItem>()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getImage(
            searchStr = searchStr,
            orientation = selectedOrientationStr,
            order = selectedOrderStr,
            type = selectedTypeStr,
            colors = selectedColorStr)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorBeluga)) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .wrapContentHeight(align = Alignment.CenterVertically),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = stringResource(id = R.string.category),
                    style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold),
                    color = Color.Black,
                    modifier = Modifier.padding(start = 20.dp))

                IconButton(modifier = Modifier.padding(end = 20.dp), onClick = {
                    showBottomSheet = true
//                    navController.navigate(Screens.PictureDetailScreen.route)
                }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "",
                        tint = Color(0XFF808080))
                }
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .background(colorBeluga)) {
                CustomSearchView(searchStr = searchStr, modifier = Modifier, onValueChange = {
                    searchStr = it
                }, performSearch = {
                    selectedItemStr = ""
                    viewModel.getImage(
                        searchStr = it,
                        orientation = selectedOrientationStr,
                        order = selectedOrderStr,
                        type = selectedTypeStr,
                        colors = selectedColorStr)
                }, onCleanClick = {
                    searchStr = ""
                    viewModel.getImage(
                        searchStr = searchStr,
                        orientation = selectedOrientationStr,
                        order = selectedOrderStr,
                        type = selectedTypeStr,
                        colors = selectedColorStr)
                })
            }

            Box(modifier = Modifier.padding(start = 10.dp, top = 10.dp, end = 10.dp)) {
                HorizontalRecyclerView(tags, onItemClick = { item ->
                    selectedItemStr = item
                    searchStr = ""
                    viewModel.getImage(
                        searchStr = searchStr,
                        orientation = selectedOrientationStr,
                        order = selectedOrderStr,
                        type = selectedTypeStr,
                        colors = selectedColorStr)
                }, selectedItem = selectedItemStr)
            }

            if (selectedOrderStr.isNotEmpty()) {
                filteredItemList.add(FilteredItem(selectedOrderStr, FilteredType.Order))
            }
            if (selectedOrientationStr.isNotEmpty()) {
                filteredItemList.add(FilteredItem(selectedOrientationStr, FilteredType.Orientation))
            }
            if (selectedTypeStr.isNotEmpty()) {
                filteredItemList.add(FilteredItem(selectedTypeStr, FilteredType.Type))
            }
            if (selectedColorStr.isNotEmpty()) {
                filteredItemList.add(FilteredItem(selectedColorStr, FilteredType.Color))
            }

            if (filteredItemList.isNotEmpty()) {
                Box(modifier = Modifier.padding(start = 10.dp, top = 10.dp, end = 10.dp)) {
                    FilteredHorizontalView(filteredItemList, onCloseClick = { type ->
                        when (type) {
                            FilteredType.Order -> {
                                selectedOrderStr = ""
                            }
                            FilteredType.Orientation -> {
                                selectedOrientationStr = ""
                            }
                            FilteredType.Type -> {
                                selectedTypeStr = ""
                            }
                            else -> {
                                selectedColorStr = ""
                            }
                        }
                    }, selectedItem = "")
                }
            }

            if (showBottomSheet) {
                ModalBottomSheet(modifier = Modifier.fillMaxWidth(), onDismissRequest = {
                    showBottomSheet = false
                }, containerColor = Color.White, sheetState = sheetState) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp)) {
                        Text(
                            text = stringResource(id = R.string.filters),
                            style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Black))
                        Text(
                            text = stringResource(id = R.string.order),
                            style = TextStyle(fontWeight = FontWeight.Medium),
                            modifier = Modifier.padding(top = 20.dp))
                        HorizontalListView(
                            orderTags,
                            modifier = Modifier.padding(top = 10.dp),
                            onItemClick = { item ->
                                selectedOrderStr = item
                            },
                            selectedItem = selectedOrderStr)
                        Text(
                            text = stringResource(id = R.string.orientation),
                            style = TextStyle(fontWeight = FontWeight.Medium),
                            modifier = Modifier.padding(top = 20.dp))
                        HorizontalListView(
                            orientationTags,
                            modifier = Modifier.padding(top = 10.dp),
                            onItemClick = { item ->
                                selectedOrientationStr = item
                            },
                            selectedItem = selectedOrientationStr)
                        Text(
                            text = stringResource(id = R.string.type),
                            style = TextStyle(fontWeight = FontWeight.Medium),
                            modifier = Modifier.padding(top = 20.dp))
                        HorizontalListView(
                            typeTags,
                            modifier = Modifier.padding(top = 10.dp),
                            onItemClick = { item ->
                                selectedTypeStr = item
                            },
                            selectedItem = selectedTypeStr)
                        Text(
                            text = stringResource(id = R.string.color),
                            style = TextStyle(fontWeight = FontWeight.Medium),
                            modifier = Modifier.padding(top = 20.dp))
                        GridView(
                            gridItems = ColorPalette.getColors(),
                            numColumns = 2,
                            selectedItem = selectedColorStr,
                            modifier = Modifier.padding(bottom = 30.dp),
                            onItemClick = {
                                selectedColorStr = it.name
                            })

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 60.dp)) {
                            Button(colors = ButtonDefaults.buttonColors(
                                containerColor = Color(
                                    0xFFE9EAEC)),
                                   border = BorderStroke(2.dp, Color.LightGray),
                                   shape = RoundedCornerShape(20.dp),
                                   modifier = Modifier
                                       .padding(end = 8.dp)
                                       .width(180.dp)
                                       .height(50.dp),
                                   onClick = {
                                       scope.launch {
                                           selectedOrderStr = ""
                                           selectedOrientationStr = ""
                                           selectedTypeStr = ""
                                           selectedColorStr = ""
                                       }.invokeOnCompletion {}
                                   }) {
                                Text(stringResource(id = R.string.reset), color = Color.Black)
                            }
                            Button(shape = RoundedCornerShape(20.dp),
                                   colors = ButtonDefaults.buttonColors(
                                       containerColor = Color(0xFF404040)),
                                   modifier = Modifier
                                       .padding(start = 8.dp)
                                       .width(180.dp)
                                       .height(50.dp),
                                   onClick = {
                                       scope.launch {
                                           sheetState.hide()
                                           showBottomSheet = false
                                       }.invokeOnCompletion {
                                           if (!sheetState.isVisible) {
                                               showBottomSheet = false
                                           }
                                           viewModel.getImage(
                                               searchStr = searchStr,
                                               orientation = selectedOrientationStr,
                                               order = selectedOrderStr,
                                               type = selectedTypeStr,
                                               colors = selectedColorStr)
                                       }
                                   }) {
                                Text(stringResource(id = R.string.apply), color = Color.White)
                            }
                        }
                    }
                }
            }

            Box(
                modifier = Modifier.padding(
                    start = 20.dp, top = 20.dp, bottom = 10.dp, end = 20.dp)) {
                StaggeredGridRecyclerView(navController, photos = result ?: emptyList())
            }
        }
    }
}


@Composable
fun HorizontalRecyclerView(items: List<String>, selectedItem: String, onItemClick: (String) -> Unit) {
    LazyRow {
        items(items) { item ->
            Box(modifier = Modifier
                .padding(10.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(
                    if (selectedItem.isNotEmpty() && selectedItem == item) {
                        Color.Black
                    } else {
                        Color.White
                    })
                .clickable {
                    if (selectedItem == item) {
                        onItemClick("")
                    } else {
                        onItemClick(item)
                    }
                }) {
                Text(
                    text = item, color = if (selectedItem.isNotEmpty() && selectedItem == item) {
                        Color.White
                    } else {
                        Color.Black
                    }, modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@Composable
fun StaggeredGridRecyclerView(navController: NavHostController, photos: List<ImageData>) {
    var selectedImage: String? by remember { mutableStateOf(null) }

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
                        .clickable {
                            selectedImage = photos[photo].webformatURL
//                            navController.navigate(Screens.PictureDetailScreen.route)
                        }) {
                    AsyncImage(
                        model = photos[photo].webformatURL,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth())
                }
            }
        },
        modifier = Modifier.fillMaxSize())

    if (selectedImage != null) {
        Log.d("selectedImage2","${selectedImage}")
        ImagePopup(selectedImage!!, onDismiss = { selectedImage = null })
    }
}
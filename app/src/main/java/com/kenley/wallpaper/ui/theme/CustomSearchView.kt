package com.kenley.wallpaper.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchView(
    search: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 30.dp, end = 20.dp)
            .clip(CircleShape)
            .background(Color(0XFF101921))

    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = search,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                placeholderColor = Color.Yellow,
                focusedLeadingIconColor = Color(0XFF888D91),
                focusedTrailingIconColor = Color(0XFF888D91),
                unfocusedLeadingIconColor = Color(0XFF888D91),
                unfocusedTrailingIconColor = Color(0XFF888D91),
                textColor = Color.Black,
                focusedIndicatorColor = Color.Black,
            ),
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
            placeholder = {
                Text(text = "Search")
            }
        )
    }

}
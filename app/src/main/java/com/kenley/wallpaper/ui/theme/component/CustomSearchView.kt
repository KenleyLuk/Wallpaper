package com.kenley.wallpaper.ui.theme.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalSoftwareKeyboardController

@Composable
fun CustomSearchView(
    searchStr: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    performSearch: (String) -> Unit,
    onCleanClick: () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
            .clip(CircleShape)
            .background(Color.White)

    ) {
        var isTextEmpty by remember { mutableStateOf(searchStr.isEmpty()) }

        TextField(modifier = Modifier.fillMaxWidth(), value = searchStr, onValueChange = {
            onValueChange(it)
//                      search = it
            isTextEmpty = it.isEmpty()
        }, colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedLeadingIconColor = Color(0XFF888D91),
            unfocusedLeadingIconColor = Color(0XFF888D91),
            focusedTrailingIconColor = Color(0XFF888D91),
            unfocusedTrailingIconColor = Color(0XFF888D91),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ), leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search, contentDescription = "")
        }, trailingIcon = {
            if (!isTextEmpty) {
                Box(modifier = Modifier
                    .padding(end = 10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0XFFE5E4E2))
                    .padding(8.dp)
                    .clickable {
                        onCleanClick()
                        isTextEmpty = true
                    }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "",
                        tint = Color(0XFF808080))
                }
            }
        }, placeholder = {
            Text(text = "Search")
        }, keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done), keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
            performSearch(searchStr)
            Log.d("keyboardActions", searchStr)
        }))
    }

}
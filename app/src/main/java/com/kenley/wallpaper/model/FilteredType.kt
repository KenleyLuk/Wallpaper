package com.kenley.wallpaper.model

data class FilteredItem(
    val name: String,
    val type: FilteredType
)

enum class FilteredType {
    Order, Orientation, Type, Color
}
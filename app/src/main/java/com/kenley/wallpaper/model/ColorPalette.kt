package com.kenley.wallpaper.model

enum class ColorPalette {
    Red,
    Orange,
    Yellow,
    Green,
    Turquoise,
    Blue,
    Lilac,
    Pink,
    Gray,
    Black,
    Brown;

    companion object {
        fun myValueOf(type: String): ColorPalette {
            val list = values()
            for (item in list) {
                if (item.name == type) {
                    return item
                }
            }
            return Brown
        }
        val colorPalette = listOf(
            "red" to "#FF0000",
            "orange" to "#FFA500",
            "yellow" to "#FFFF00",
            "green" to "#008000",
            "turquoise" to "#40E0D0",
            "blue" to "#0000FF",
            "lilac" to "#C8A2C8",
            "pink" to "#FFC0CB",
            "gray" to "#808080",
            "black" to "#000000",
            "brown" to "#A52A2A"
        )

        fun getColors(): List<ColorPalette> {
            val colors = listOf(
                Red,
                Orange,
                Yellow,
                Green,
                Turquoise,
                Blue,
                Lilac,
                Pink,
                Gray,
                Black,
                Brown
            )

            return colors
        }

        fun getColorResource(color: ColorPalette): String {
            return when (color) {
                Red -> "#FF0000"
                Orange -> "#FFA500"
                Yellow -> "#FFFF00"
                Green -> "#008000"
                Turquoise -> "#40E0D0"
                Blue -> "#0000FF"
                Lilac -> "#C8A2C8"
                Pink -> "#FFC0CB"
                Gray -> "#808080"
                Black -> "#000000"
                Brown -> "#A52A2A"
            }
        }
    }


}
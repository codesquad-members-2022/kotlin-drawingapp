package com.example.kotlin_drawingapp.shapemodel

data class Color(val r: Int, val g: Int, val b: Int) {
    init {
        if (r < 0 || r > 255
            || g < 0 || g > 255
            || b < 0 || b > 255
        ) {
            throw Exception("0~255만 가능합니다.")
        }
    }

    fun getRgb(): Int {
        val red = r shl 16 and 0x00FF0000 //Shift red 16-bits and mask out other stuff
        val green = g shl 8 and 0x0000FF00 //Shift Green 8-bits and mask out other stuff
        val blue = b and 0x000000FF //Mask out anything not blue.
        return red or green or blue //0xFF000000 for 100% Alpha. Bitwise OR everything together.
    }
}

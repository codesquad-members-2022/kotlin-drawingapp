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
}

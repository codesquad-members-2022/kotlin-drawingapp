package com.example.kotlin_drawingapp.square

data class Square(val id: ID, val point: Point, val size: Size, val rgb: RGB, val alpha: Alpha) {
    override fun toString(): String {
        return "(${id.createID()}), " +
                "X:${point.createPoint()[0]}, " +
                "Y:${point.createPoint()[1]}, " +
                "W:${size.createSize()[0]}, " +
                "H:${size.createSize()[1]}, " +
                "R:${rgb.createRGB()[0]}, " +
                "G:${rgb.createRGB()[1]}, " +
                "B:${rgb.createRGB()[2]}, " +
                "Alpha:${alpha.createAlpha()}"
    }
}

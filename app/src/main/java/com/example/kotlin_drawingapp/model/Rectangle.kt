package com.example.kotlin_drawingapp.model

import android.graphics.Point
import android.util.Size

data class Rectangle(
    val id: String,
    val size: Size,
    var point: Point,
    val rgb: Color,
    var alpha: Int
) {
    init {
        if (alpha !in 1..10) throw Exception("1~10사이만 가능합니다.")
    }

    override fun toString(): String {
        return String.format("(%s), X:%d,Y:%d, W:%d, H:%d, R:%d, G:%d, B:%d, Alpha: %d",
        id, point.x, point.y, size.width, size.height, rgb.r, rgb.g, rgb.b, alpha)
    }
}

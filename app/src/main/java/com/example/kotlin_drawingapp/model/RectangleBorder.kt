package com.example.kotlin_drawingapp.model

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.util.Size

data class RectangleBorder(
    var size: Size = Size(0, 0),
    var point: Point = Point(0, 0),
) {
    val paint = Paint()
    init {
        paint.strokeWidth = 5.0f
        paint.style = Paint.Style.STROKE
        paint.color = Color.BLUE
    }
}

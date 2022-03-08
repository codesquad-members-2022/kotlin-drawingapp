package com.example.kotlin_drawingapp.model.draw

import android.graphics.Paint
import android.graphics.Point
import android.util.Size
import com.example.kotlin_drawingapp.model.Color

sealed class DrawObject {
    enum class Category {
        RECTANGLE,
        IMAGE
    }

    var selected: Boolean = false
    val borderPaint = Paint().apply {
        strokeWidth = 5.0f
        style = Paint.Style.STROKE
        color = android.graphics.Color.BLUE
    }
    lateinit var currentSize: Size
    lateinit var currentPoint: Point
    data class Rectangle(
        val id: String,
        var size: Size,
        var point: Point,
        val rgb: Color,
        var alpha: Int,
    ) : DrawObject() {

        init {
            if (alpha !in 1..10) throw Exception("1~10사이만 가능합니다.")
            currentSize = size
            currentPoint = point
        }

        override fun toString(): String {
            return String.format(
                "(%s), X:%d,Y:%d, W:%d, H:%d, R:%d, G:%d, B:%d, Alpha: %d",
                id, point.x, point.y, size.width, size.height, rgb.r, rgb.g, rgb.b, alpha
            )
        }
    }
}

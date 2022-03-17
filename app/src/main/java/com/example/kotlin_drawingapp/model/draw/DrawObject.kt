package com.example.kotlin_drawingapp.model.draw

import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.Point
import android.util.Size
import com.example.kotlin_drawingapp.model.Color

sealed class DrawObject {
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
        private var size: Size,
        private var point: Point,
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

        override fun createNewDrawObject(size: Size, point: Point): Rectangle {
            return Rectangle(id, size, point, rgb, alpha)
        }
    }

    data class Image(
        val id: String,
        private var size: Size,
        private var point: Point,
        var alpha: Int,
        var bitmap: Bitmap
    ) : DrawObject() {
        init {
            if (alpha !in 1..10) throw Exception("1~10사이만 가능합니다.")
            currentSize = size
            currentPoint = point
        }

        override fun createNewDrawObject(size: Size, point: Point): Image {
            return Image(id, size, point, alpha, bitmap)
        }
    }

    data class CustomTextView(
        val id: String,
        private var size: Size,
        private var point: Point,
        var endPos: Int,
        val paint: Paint,
        val text: String,
        var alpha: Int
    ) : DrawObject() {
        init {
            currentSize = size
            currentPoint = point
        }

        override fun createNewDrawObject(size: Size, point: Point): DrawObject {
            return CustomTextView(id, size, point, endPos, paint, text, alpha)
        }
    }

    abstract fun createNewDrawObject(size: Size, point: Point): DrawObject
}

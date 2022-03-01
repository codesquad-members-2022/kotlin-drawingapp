package com.example.kotlindrawingapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.example.kotlindrawingapp.square.Square


class CustomCanvas : View {

    val paint = Paint()
    val path = Path()

    constructor(context: Context) : super(context) {}
    constructor(context: Context, attr: AttributeSet) : super(context, attr) {}

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.color = Color.GREEN
        canvas?.drawPath(path, paint)
    }

    fun drawRectangle(square: Square) {
        val x = square.point.x
        val y = square.point.y
        val width = square.size.width
        val height = square.size.height
        path.addRect(
            x.toFloat(),
            y.toFloat(),
            x.toFloat() + width,
            y.toFloat() + height,
            Path.Direction.CCW
        )
        invalidate()
    }
}
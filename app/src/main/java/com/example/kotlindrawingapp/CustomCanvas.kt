package com.example.kotlindrawingapp

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.kotlindrawingapp.square.Square


class CustomCanvas : View {

    private var paint: Paint = Paint()
    private var selectedPaint: Paint = Paint()
    private var rect: RectF = RectF()
    private var flag = false

    constructor(context: Context) : super(context)
    constructor(context: Context, attr: AttributeSet) : super(context, attr)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (flag) {
            canvas?.drawRect(rect, selectedPaint)
            canvas?.drawRect(rect, paint)
            return
        }
        canvas?.drawRect(rect, paint)
    }

    fun drawRectangle(square: Square) {
        flag = false
        val x = square.point.x
        val y = square.point.y
        val width = square.size.width
        val height = square.size.height
        rect = RectF(x.toFloat(), y.toFloat(), (x + width).toFloat(), (y + height).toFloat())
        paint(square)
        invalidate()
    }

    private fun paint(square: Square) {
        val alpha = square.alpha.alpha
        val red = square.rgb.red
        val green = square.rgb.green
        val blue = square.rgb.blue
        paint.color = Color.argb(alpha * 10, red, green, blue)
    }

    fun selectRectangle() {
        flag = true
        selectedPaint.strokeWidth = 15F
        selectedPaint.style = Paint.Style.STROKE
        selectedPaint.color = Color.BLACK
        invalidate()
    }

    fun removeStroke() {
        flag = false
        selectedPaint = Paint()
        invalidate()
    }
}
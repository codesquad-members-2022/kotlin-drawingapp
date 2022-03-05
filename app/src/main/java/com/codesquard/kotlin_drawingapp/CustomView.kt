package com.codesquard.kotlin_drawingapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View

class CustomView(context: Context, attr: AttributeSet) : View(context, attr) {

    private val rectangleList = mutableListOf<Rectangle>()

    fun addNewRect(newRect: Rectangle) {
        rectangleList.add(newRect)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawRectangle(rectangleList, canvas)
    }

    private fun drawRectangle(rectangleList: MutableList<Rectangle>, canvas: Canvas?) {
        rectangleList.forEach {
            val paint = Paint()
            val alpha = it.getAlpha()
            val r = it.getColor()[0]
            val g = it.getColor()[1]
            val b = it.getColor()[2]
            paint.color = Color.argb(alpha, r, g, b)

            val x = it.getPoint()[0]
            val y = it.getPoint()[1]
            val width = it.getSize()[0] + x
            val height = it.getSize()[1] + y
            canvas?.drawRect(x, y, width, height, paint)

            if (it.getStatus()) {
                val strokePaint = Paint()
                strokePaint.style = Paint.Style.STROKE
                strokePaint.color = Color.rgb(r, g, b)
                strokePaint.strokeWidth = 10f
                canvas?.drawRect(x, y, width, height, strokePaint)
            }
        }
    }
}
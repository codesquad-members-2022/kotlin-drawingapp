package com.codesquard.kotlin_drawingapp.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.codesquard.kotlin_drawingapp.model.Rectangle
import com.codesquard.kotlin_drawingapp.model.TextRectangle

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
            val paint = CustomViewUtils.setPaint(it)
            val size = CustomViewUtils.setSize(it)
            CustomViewUtils.setStroke(it, canvas)
            CustomViewUtils.setSpecificRect(it, size, paint, canvas)
        }
    }

    fun getViewSize(): Array<Int> {
        return arrayOf(this.width, this.height)
    }

    fun getTextSize(textRect: Rectangle): Array<Int> {
        val textRect = textRect as TextRectangle
        val text = textRect.getText()
        val textBound = Rect()
        val paint = Paint()
        paint.run {
            this.textSize = 50f
            this.getTextBounds(text, 0, text.length, textBound)
        }
        return arrayOf(textBound.width(), textBound.height())
    }
}


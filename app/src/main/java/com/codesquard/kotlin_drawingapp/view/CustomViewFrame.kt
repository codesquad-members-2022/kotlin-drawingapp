package com.codesquard.kotlin_drawingapp.view

import android.graphics.*
import com.codesquard.kotlin_drawingapp.model.PhotoRectangle
import com.codesquard.kotlin_drawingapp.model.Rectangle
import com.codesquard.kotlin_drawingapp.model.TextRectangle

interface CustomViewFrame {

    fun setPaint(rect: Rectangle): Paint {
        val paint = Paint()
        paint.color = Color.argb(rect.alphaValue, rect.color[0], rect.color[1], rect.color[2])
        return paint
    }

    fun setSize(rect: Rectangle): RectF {
        val startX = rect.point[0]
        val startY = rect.point[1]
        val endX = startX + rect.size[0]
        val endY = startY + rect.size[1]
        val tempRect = RectF()
        tempRect.set(startX, startY, endX, endY)
        return tempRect
    }

    fun setStroke(rect: Rectangle, canvas: Canvas?) {
        if (rect.isSelected) {
            val paint = Paint()
            paint.apply {
                color = Color.rgb(rect.color[0], rect.color[1], rect.color[2])
                style = Paint.Style.STROKE
                strokeWidth = 5f
            }
            val tempRect = RectF()
            val startX = rect.point[0]
            val startY = rect.point[1]
            val endX = startX + rect.size[0]
            val endY = startY + rect.size[1]
            tempRect.set(startX - 2.5f, startY - 2.5f, endX + 2.5f, endY + 2.5f)
            canvas?.drawRect(tempRect, paint)
        }
    }

    fun setSpecificRect(rect: Rectangle, size: RectF, paint: Paint, canvas: Canvas?) {
        when (rect) {
            is PhotoRectangle -> {
                val photo: Bitmap = rect.getPhoto() ?: return
                canvas?.drawBitmap(photo, null, size, paint)
            }
            is TextRectangle -> {
                val text = rect.getText()
                val textBound = Rect()
                paint.textSize = 50f
                paint.getTextBounds(text, 0, text.length, textBound)
                val endY = textBound.top.toFloat()
                val startX = rect.point[0]
                val startY = rect.point[1]
                canvas?.drawText(text, startX, startY - endY, paint)
            }
            else -> {
                canvas?.drawRect(size, paint)
            }
        }
    }
}
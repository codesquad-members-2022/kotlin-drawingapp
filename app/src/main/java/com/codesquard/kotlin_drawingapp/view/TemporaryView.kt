package com.codesquard.kotlin_drawingapp.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.codesquard.kotlin_drawingapp.model.PhotoRectangle
import com.codesquard.kotlin_drawingapp.model.Rectangle
import com.codesquard.kotlin_drawingapp.model.TextRectangle

class TemporaryView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private var tempRect: Rectangle? = null

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        dragRectangle(tempRect, canvas)
    }

    private fun dragRectangle(tempRect: Rectangle?, canvas: Canvas?) {
        tempRect?.apply temp@{
            val startX = tempRect.point[0]
            val startY = tempRect.point[1]
            val endX = startX + tempRect.size[0]
            val endY = startY + tempRect.size[1]
            val paint = Paint().apply {
                this.color = Color.argb(
                    50,
                    this@temp.color[0],
                    this@temp.color[1],
                    this@temp.color[2]
                )
            }

            when (this) {
                is PhotoRectangle -> {
                    val photo: Bitmap = this.getPhoto() ?: return
                    val rect = RectF(startX, startY, endX, endY)
                    canvas?.drawBitmap(photo, null, rect, paint)
                }
                is TextRectangle -> {
                    val text = this.getText()
                    val textBound = Rect()
                    paint.textSize = 50f
                    paint.getTextBounds(text, 0, text.length, textBound)
                    val rY = textBound.top.toFloat()
                    canvas?.drawText(text, startX, startY - rY, paint)
                }
                else -> {
                    canvas?.drawRect(startX, startY, endX, endY, paint)
                }
            }
        }
    }

    fun setTempRect(tempRect: Rectangle?) {
        this.tempRect = tempRect
    }

}
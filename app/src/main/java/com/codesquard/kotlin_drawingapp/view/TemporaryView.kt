package com.codesquard.kotlin_drawingapp.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.codesquard.kotlin_drawingapp.model.Rectangle

class TemporaryView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private var tempRect: Rectangle? = null

    fun setTempRect(tempRect: Rectangle?) {
        this.tempRect = tempRect
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        dragRectangle(tempRect, canvas)
    }

    fun setPaint(rect: Rectangle): Paint {
        val paint = Paint()
        paint.color = Color.argb(50, rect.color[0], rect.color[1], rect.color[2])
        return paint
    }

    private fun dragRectangle(tempRect: Rectangle?, canvas: Canvas?) {
        tempRect?.let {
            val paint = setPaint(it)
            val size = CustomViewUtils.setSize(it)
            CustomViewUtils.setSpecificRect(it, size, paint, canvas)
        }
    }
}
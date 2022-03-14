package com.codesquard.kotlin_drawingapp.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.codesquard.kotlin_drawingapp.model.PhotoRectangle
import com.codesquard.kotlin_drawingapp.model.Rectangle
import com.codesquard.kotlin_drawingapp.model.TextRectangle

class TemporaryView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet),
    CustomViewFrame {

    private var tempRect: Rectangle? = null

    fun setTempRect(tempRect: Rectangle?) {
        this.tempRect = tempRect
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        dragRectangle(tempRect, canvas)
    }

    private fun dragRectangle(tempRect: Rectangle?, canvas: Canvas?) {
        tempRect?.let {
            val paint = setPaint(it)
            val size = setSize(it)
            setSpecificRect(it, size, paint, canvas)
        }
    }
}
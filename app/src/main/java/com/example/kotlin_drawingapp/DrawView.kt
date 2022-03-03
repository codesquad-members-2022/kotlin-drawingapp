package com.example.kotlin_drawingapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.kotlin_drawingapp.model.Rectangle

class DrawView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val drawnRectangleList = mutableListOf<Rectangle>()

    fun drawRectangle(rectangle: Rectangle) {
        drawnRectangleList.add(rectangle)
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        for (rect in drawnRectangleList) {
            canvas?.apply {
                val paint = setRectanglePaint(rect)
                drawRect(
                    rect.point.x.toFloat(),
                    rect.point.y.toFloat(),
                    rect.size.width.toFloat(),
                    rect.size.height.toFloat(),
                    paint
                )
            }
        }

        super.onDraw(canvas)
    }

    private fun setRectanglePaint(rect: Rectangle): Paint {
        val paint = Paint()
        paint.isAntiAlias = true
        val alpha = (255.0 * (rect.alpha / 10.0)).toInt()
        paint.color = Color.argb(alpha, rect.rgb.r, rect.rgb.g, rect.rgb.b)

        return paint
    }
}

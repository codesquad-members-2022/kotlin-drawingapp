package com.example.kotlin_drawingapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.kotlin_drawingapp.model.Rectangle
import com.example.kotlin_drawingapp.model.RectangleBorder

class DrawView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    interface SetOnDrawViewTouchListener {
        fun onClick(point: PointF)
    }

    private var drawViewTouchListener: SetOnDrawViewTouchListener? = null
    fun setOnRectangleClickListener(listener: SetOnDrawViewTouchListener) {
        drawViewTouchListener = listener
    }

    private var drawnRectangleBorderList = listOf<RectangleBorder>()
    private var drawnRectangleList = listOf<Rectangle>()
    fun drawRectangle(rectangles: List<Rectangle>) {
        drawnRectangleList = rectangles
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

package com.example.kotlin_drawingapp

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.kotlin_drawingapp.model.Rectangle

class DrawView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    interface OnTouchListener {
        fun onClick(point: PointF)
    }

    private var touchListener: OnTouchListener? = null
    fun setOnTouchListener(listener: OnTouchListener) {
        touchListener = listener
    }

    private var drawnRectangleList = listOf<Rectangle>()
    fun drawRectangle(rectangles: List<Rectangle>) {
        drawnRectangleList = rectangles
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        val selectedRectangle = mutableListOf<Rectangle>()

        for (rect in drawnRectangleList) {
            if (rect.selected) {
                selectedRectangle.add(rect)
            }

            canvas?.apply {
                val paint = setRectanglePaint(rect)
                drawRect(
                    rect.point.x.toFloat(),
                    rect.point.y.toFloat(),
                    rect.point.x.toFloat() + rect.size.width.toFloat(),
                    rect.point.y.toFloat() + rect.size.height.toFloat(),
                    paint
                )
            }
        }

        for (rect in selectedRectangle) {
            canvas?.apply {
                drawRect(
                    rect.point.x.toFloat(),
                    rect.point.y.toFloat(),
                    rect.point.x.toFloat() + rect.size.width.toFloat(),
                    rect.point.y.toFloat() + rect.size.height.toFloat(),
                    rect.borderPaint
                )
            }
        }

        super.onDraw(canvas)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                touchListener?.onClick(PointF(event.x, event.y))
            }
        }
        return super.onTouchEvent(event)
    }

    private fun setRectanglePaint(rect: Rectangle): Paint {
        val paint = Paint()
        paint.isAntiAlias = true
        val alpha = (255.0 * (rect.alpha / 10.0)).toInt()
        paint.color = Color.argb(alpha, rect.rgb.r, rect.rgb.g, rect.rgb.b)

        return paint
    }
}

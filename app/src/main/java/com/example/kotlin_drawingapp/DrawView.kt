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
import com.example.kotlin_drawingapp.model.RectangleBorder

class DrawView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    interface OnTouchListener {
        fun onClick(point: PointF)
    }

    private var touchListener: OnTouchListener? = null
    fun setOnTouchListener(listener: OnTouchListener) {
        touchListener = listener
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
                    rect.point.x.toFloat() + rect.size.width.toFloat(),
                    rect.point.y.toFloat() + rect.size.height.toFloat(),
                    paint
                )
            }
        }

        for (border in drawnRectangleBorderList) {
            canvas?.drawRect(
                border.point.x.toFloat(),
                border.point.y.toFloat(),
                border.point.x.toFloat() + border.size.width.toFloat(),
                border.point.y.toFloat() + border.size.height.toFloat(),
                border.paint
            )
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

    fun drawRectangleBorder(rectangleBorderList: List<RectangleBorder>) {
        drawnRectangleBorderList = rectangleBorderList
        invalidate()
    }
}

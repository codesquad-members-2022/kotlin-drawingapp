package com.example.kotlin_drawingapp.model.draw

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    interface OnTouchListener {
        fun onClick(point: PointF)
    }

    private var touchListener: OnTouchListener? = null
    fun setOnTouchListener(listener: OnTouchListener) {
        touchListener = listener
    }

    private var drawnObjectList = listOf<DrawObject>()
    fun draw(drawObjects: List<DrawObject>) {
        drawnObjectList = drawObjects
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        val selectedDrawObject = mutableListOf<DrawObject>()

        for (drawObject in drawnObjectList) {
            if (drawObject.selected) {
                selectedDrawObject.add(drawObject)
            }

            when (drawObject) {
                is DrawObject.Rectangle -> drawRectangle(canvas, drawObject)
            }
        }

        for (rect in selectedDrawObject) {
            canvas?.apply {
                drawRect(
                    rect.currentPoint.x.toFloat(),
                    rect.currentPoint.y.toFloat(),
                    rect.currentPoint.x.toFloat() + rect.currentSize.width.toFloat(),
                    rect.currentPoint.y.toFloat() + rect.currentSize.height.toFloat(),
                    rect.borderPaint
                )
            }
        }

        super.onDraw(canvas)
    }

    private fun drawRectangle(canvas: Canvas?, rectangle: DrawObject.Rectangle) {
        canvas?.apply {
            val paint = setRectanglePaint(rectangle)
            drawRect(
                rectangle.point.x.toFloat(),
                rectangle.point.y.toFloat(),
                rectangle.point.x.toFloat() + rectangle.size.width.toFloat(),
                rectangle.point.y.toFloat() + rectangle.size.height.toFloat(),
                paint
            )
        }
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

    private fun setRectanglePaint(rect: DrawObject.Rectangle): Paint {
        val paint = Paint()
        paint.isAntiAlias = true
        val alpha = (255.0 * (rect.alpha / 10.0)).toInt()
        paint.color = Color.argb(alpha, rect.rgb.r, rect.rgb.g, rect.rgb.b)

        return paint
    }
}

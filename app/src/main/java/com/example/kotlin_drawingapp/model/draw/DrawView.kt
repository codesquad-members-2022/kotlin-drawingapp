package com.example.kotlin_drawingapp.model.draw

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    interface OnDrawViewTouchListener {
        fun onClick(point: PointF)
    }

    private var drawViewTouchListener: OnDrawViewTouchListener? = null
    var currentSelectedDrawObject: DrawObject? = null

    fun setOnDrawViewTouchListener(listenerDrawView: OnDrawViewTouchListener) {
        drawViewTouchListener = listenerDrawView
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
                is DrawObject.Image -> drawImage(canvas, drawObject)
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

    private fun drawImage(canvas: Canvas?, image: DrawObject.Image) {
        canvas?.apply {
            val paint = Paint()
            paint.isAntiAlias = true
            val alpha = (255.0 * (image.alpha / 10.0)).toInt()
            paint.alpha = alpha

            val rect = Rect(
                image.currentPoint.x,
                image.currentPoint.y,
                image.currentPoint.x + image.currentSize.width,
                image.currentPoint.y + image.currentSize.height
            )
            drawBitmap(image.bitmap, null, rect, paint)
        }
    }

    private fun drawRectangle(canvas: Canvas?, rectangle: DrawObject.Rectangle) {
        canvas?.apply {
            val paint = setRectanglePaint(rectangle)
            drawRect(
                rectangle.currentPoint.x.toFloat(),
                rectangle.currentPoint.y.toFloat(),
                rectangle.currentPoint.x.toFloat() + rectangle.currentSize.width.toFloat(),
                rectangle.currentPoint.y.toFloat() + rectangle.currentSize.height.toFloat(),
                paint
            )
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                drawViewTouchListener?.onClick(PointF(event.x, event.y))
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

    private fun copyForTemporaryDrawObject(drawObject: DrawObject?): DrawObject? {
        return when (drawObject) {
            is DrawObject.Rectangle -> {
                drawObject.copy(
                    size = Size(drawObject.currentSize.width, drawObject.currentSize.height),
                    point = Point(drawObject.currentPoint.x, drawObject.currentPoint.y),
                    rgb = com.example.kotlin_drawingapp.model.Color(
                        drawObject.rgb.r,
                        drawObject.rgb.g,
                        drawObject.rgb.b
                    ),
                    alpha = 5
                )
            }
            is DrawObject.Image -> {
                drawObject.copy(
                    size = Size(drawObject.currentSize.width, drawObject.currentSize.height),
                    point = Point(drawObject.currentPoint.x, drawObject.currentPoint.y),
                    alpha = 5
                )
            }
            else -> null
        }
    }
}

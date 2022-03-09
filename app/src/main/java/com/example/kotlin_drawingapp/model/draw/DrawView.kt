package com.example.kotlin_drawingapp.model.draw

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Size
import android.view.MotionEvent
import android.view.View

class DrawView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    interface OnDrawViewTouchListener {
        fun onClick(point: PointF)
    }

    interface OnDrawViewPointUpdateListener {
        fun update(target: DrawObject, point: Point)
    }

    private var drawViewPointUpdateListener: OnDrawViewPointUpdateListener? = null
    private var drawViewTouchListener: OnDrawViewTouchListener? = null
    var currentSelectedDrawObject: DrawObject? = null
    private var temporaryDrawObject: DrawObject? = null
    private var lastPosX = 0
    private var lastPosY = 0

    fun setOnDrawViewTouchListener(listenerDrawView: OnDrawViewTouchListener) {
        drawViewTouchListener = listenerDrawView
    }

    fun setOnDrawViewUpdateListener(listenerPoint: OnDrawViewPointUpdateListener) {
        drawViewPointUpdateListener = listenerPoint
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

        drawTemporaryDrawObject(canvas)
        super.onDraw(canvas)
    }

    private fun drawTemporaryDrawObject(canvas: Canvas?) {
        temporaryDrawObject?.let {
            when (temporaryDrawObject) {
                is DrawObject.Rectangle -> drawRectangle(canvas, it as DrawObject.Rectangle)
                is DrawObject.Image -> drawImage(canvas, it as DrawObject.Image)
                else -> return
            }
        }
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
        super.onTouchEvent(event)

        val touchPoint = event?.pointerCount ?: 0
        when (event?.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                drawViewTouchListener?.onClick(PointF(event.x, event.y))
            }

            MotionEvent.ACTION_POINTER_DOWN -> {
                if (touchPoint == 2) {
                    temporaryDrawObject = copyForTemporaryDrawObject(currentSelectedDrawObject)
                    temporaryDrawObject?.let {
                        lastPosX = event.getX(0).toInt()
                        lastPosY = event.getY(0).toInt()
                    }
                }
            }

            MotionEvent.ACTION_MOVE -> {
                if (touchPoint == 2) {
                    temporaryDrawObject?.apply {
                        currentPoint.x += event.getX(0).toInt() - lastPosX
                        currentPoint.y += event.getY(0).toInt() - lastPosY

                        lastPosX = event.getX(0).toInt()
                        lastPosY = event.getY(0).toInt()
                    }
                    invalidate()
                }
            }

            MotionEvent.ACTION_UP -> {
                currentSelectedDrawObject?.let { current ->
                    temporaryDrawObject?.let { temp ->
                        drawViewPointUpdateListener?.update(current, temp.currentPoint)
                    }
                }
                temporaryDrawObject = null
                invalidate()
            }
        }
        return true
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

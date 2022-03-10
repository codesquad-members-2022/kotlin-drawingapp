package com.example.kotlindrawingapp.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.kotlindrawingapp.domain.figure.picture.Picture
import com.example.kotlindrawingapp.domain.figure.plane.Plane
import com.example.kotlindrawingapp.domain.figure.Figure
import com.example.kotlindrawingapp.domain.figure.Point
import com.example.kotlindrawingapp.domain.figure.square.Square

class CustomCanvas(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private lateinit var listener: Movable
    private lateinit var plane: Plane
    private var paint: Paint = Paint()
    private var selectedPaint: Paint = Paint()
    private var temporary: Figure? = null
    private var moveX: Float = 0F
    private var moveY: Float = 0F

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return
        temporary?.let { figure -> drawTemporary(canvas, figure) }
        plane.squares.forEach { figure -> drawFigure(canvas, figure) }
    }

    private fun drawTemporary(canvas: Canvas, figure: Figure) {
        val width = figure.size.width
        val height = figure.size.height
        when (figure) {
            is Square -> canvas.drawRect(
                moveX,
                moveY,
                moveX + width,
                moveY + height,
                temporaryPaint(figure)
            )
            is Picture -> {
                val bitmap = Picture.byteArrayToBitmap(figure.memory)
                val newBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true)
                canvas.drawBitmap(newBitmap, moveX, moveY, temporaryPaint(figure))
            }
        }
    }

    private fun drawFigure(canvas: Canvas, figure: Figure) {
        val x = figure.point.x
        val y = figure.point.y
        val width = figure.size.width
        val height = figure.size.height
        when (figure) {
            is Square -> canvas.drawRect(x, y, (x + width), (y + height), paintSquare(figure))
            is Picture -> {
                val bitmap = Picture.byteArrayToBitmap(figure.memory)
                val newBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true)
                canvas.drawBitmap(newBitmap, x, y, paintSquare(figure))
            }
        }
        if (plane.selectedSquare.value == figure) {
            canvas.drawRect(x, y, (x + width), (y + height), selectedPaintSquare())
        }
    }

    fun drawRectangle(plane: Plane) {
        this.plane = plane
        invalidate()
    }

    private fun paintSquare(square: Figure): Paint {
        val alpha = square.alpha.alpha
        square.rgb?.let {
            paint.color = Color.argb(alpha * 25, it.red, it.green, it.blue)
        } ?: run {
            paint.alpha = alpha * 25
        }
        return paint
    }

    private fun temporaryPaint(square: Figure): Paint {
        square.rgb?.let {
            paint.color = Color.argb(5 * 25, it.red, it.green, it.blue)
        } ?: run {
            paint.alpha = 5 * 25
        }
        return paint
    }

    private fun selectedPaintSquare(): Paint {
        selectedPaint.strokeWidth = 5F
        selectedPaint.style = Paint.Style.STROKE
        selectedPaint.color = Color.BLACK
        return selectedPaint
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event ?: return false
        moveX = event.x
        moveY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                plane.touchPoint(Point(moveX, moveY))
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                plane.selectedSquare.value?.let { figure ->
                    temporary = figure
                    listener.move(moveX, moveY)
                    invalidate()
                }
            }
            MotionEvent.ACTION_UP -> {
                val selectedFigure = plane.selectedSquare.value
                selectedFigure?.let { selectedFigure ->
                    temporary?.let {
                        listener.move(moveX, moveY, it, selectedFigure)
                        temporary = null
                    }
                }
                temporary = null
            }
        }
        return true
    }

    fun setListener(listener: Movable) {
        this.listener = listener
    }
}
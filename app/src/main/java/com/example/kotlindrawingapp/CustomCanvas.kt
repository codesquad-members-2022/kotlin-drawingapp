package com.example.kotlindrawingapp

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

    private lateinit var plane: Plane
    private var paint: Paint = Paint()
    private var selectedPaint: Paint = Paint()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return
        plane.squares.forEach { figure ->
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
                canvas.drawRect(x, y, (x + width), (y + height), selectRectangle())
            }
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

    private fun selectRectangle(): Paint {
        selectedPaint.strokeWidth = 15F
        selectedPaint.style = Paint.Style.STROKE
        selectedPaint.color = Color.BLACK
        return selectedPaint
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> { }
            MotionEvent.ACTION_MOVE -> { }
            MotionEvent.ACTION_UP -> {
                val (x, y) = Pair(event.x, event.y)
                plane.touchPoint(Point(x, y))
                invalidate()
            }
        }
        return true
    }
}
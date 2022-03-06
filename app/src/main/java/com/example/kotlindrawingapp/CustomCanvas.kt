package com.example.kotlindrawingapp

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.kotlindrawingapp.square.Plane
import com.example.kotlindrawingapp.presenter.Presenter
import com.example.kotlindrawingapp.square.Point
import com.example.kotlindrawingapp.square.Square

class CustomCanvas(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private lateinit var plane: Plane
    private var paint: Paint = Paint()
    private var selectedPaint: Paint = Paint()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return
        plane.squares.forEach { square ->
            val x = square.point.x
            val y = square.point.y
            val width = square.size.width
            val height = square.size.height
            canvas.drawRect(x, y, (x + width), (y + height), paintSquare(square))
            if (plane.selectedSquare.value == square) {
                canvas.drawRect(x, y, (x + width), (y + height), selectRectangle())
            }
        }
    }

    fun drawRectangle(plane: Plane) {
        this.plane = plane
        invalidate()
    }

    private fun paintSquare(square: Square): Paint {
        val alpha = square.alpha.alpha
        val red = square.rgb.red
        val green = square.rgb.green
        val blue = square.rgb.blue
        paint.color = Color.argb(alpha * 25, red, green, blue)
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
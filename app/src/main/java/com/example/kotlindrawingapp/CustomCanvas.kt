package com.example.kotlindrawingapp

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.kotlindrawingapp.plane.Plane
import com.example.kotlindrawingapp.presenter.Contract
import com.example.kotlindrawingapp.presenter.Presenter
import com.example.kotlindrawingapp.square.Square

class CustomCanvas(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private lateinit var square: Square
    private lateinit var presenter: Contract.Presenter
    private lateinit var plane: Plane
    private var paint: Paint = Paint()
    private var selectedPaint: Paint = Paint()
    private var index: Int = -1

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return
        plane.squares.forEachIndexed { idx, square ->
            val x = square.point.x.toFloat()
            val y = square.point.y.toFloat()
            val width = square.size.width.toFloat()
            val height = square.size.height.toFloat()
            canvas?.drawRect(x, y, (x + width), (y + height), paintSquare(square))
            if (idx == index) {
                canvas?.drawRect(x, y, (x + width), (y + height), selectRectangle())
            }
        }
    }

    fun drawRectangle(_square: Square) {
        this.square = _square
        invalidate()
    }

    private fun paintSquare(square: Square): Paint {
        val alpha = square.alpha.alpha
        val red = square.rgb.red
        val green = square.rgb.green
        val blue = square.rgb.blue
        paint.color = Color.argb(alpha * 10, red, green, blue)
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
                index = plane.contain(x, y)
                presenter.loadBoard(index)
                invalidate()
            }
        }
        return true
    }

    fun setPresent(presenter: Presenter) {
        this.presenter = presenter
        plane = presenter.repository.plane
    }
}
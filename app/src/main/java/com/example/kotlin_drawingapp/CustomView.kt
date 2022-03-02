package com.example.kotlin_drawingapp

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.MotionEvent
import android.view.View

@SuppressLint("ViewConstructor")
class CustomView(context: Context?, check: Listener) : View(context) {
    var checkTouch: Listener = check

    private var flag = false
    private val squareModelFactory = SquareModelFactory()
    private val plane = Plane()

    private val rectWidth = squareModelFactory.createSquareObject()[1].toInt()
    private val rectHeight = squareModelFactory.createSquareObject()[2].toInt()

    private val firstRandomLocation = plane.randomLocation()
    private val secondRandomLocation = plane.randomLocation()
    private val thirdRandomLocation = plane.randomLocation()

    private val firstRect = plane.makeRect(firstRandomLocation[0], firstRandomLocation[1], firstRandomLocation[0] + rectWidth, firstRandomLocation[1] + rectHeight)
    private val secondRect = plane.makeRect(secondRandomLocation[0], secondRandomLocation[1], secondRandomLocation[0] + rectWidth, secondRandomLocation[1] + rectHeight)
    private val thirdRect = plane.makeRect(thirdRandomLocation[0], thirdRandomLocation[1], thirdRandomLocation[0] + rectWidth, thirdRandomLocation[1] + rectHeight)

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        val firstPaint = Paint()
        val secondPaint = Paint()
        val thirdPaint = Paint()

        firstPaint.style = Paint.Style.FILL
        secondPaint.style = Paint.Style.FILL
        thirdPaint.style = Paint.Style.FILL
        firstPaint.color = Color.GRAY
        secondPaint.color = Color.BLUE
        thirdPaint.color = Color.RED

        canvas?.drawRect(firstRect, firstPaint)
        canvas?.drawRect(secondRect, secondPaint)
        canvas?.drawRect(thirdRect, thirdPaint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val locationX = event?.x?.toInt()
        val locationY = event?.y?.toInt()

        when (event?.action) {
            MotionEvent.ACTION_DOWN ->
                when {
                    firstRect.contains(locationX!!, locationY!!) -> {
                        checkTouch.check(true)
                    }
                    secondRect.contains(locationX!!, locationY!!) -> {
                        checkTouch.check(true)
                    }
                    thirdRect.contains(locationX!!, locationY!!) -> {
                        checkTouch.check(true)
                    }
                    else -> {
                        checkTouch.check(false)
                    }
                }
        }
        return flag
    }
}
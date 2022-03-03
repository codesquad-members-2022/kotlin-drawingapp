package com.example.kotlin_drawingapp

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.MotionEvent
import android.view.View
import com.example.kotlin_drawingapp.square.Size

@SuppressLint("ViewConstructor")
class Canvas(context: Context?, private val contract: Contract.View) : View(context), Contract.Presenter {

    private var flag = false
    private val plane = Plane()
    private val size = Size()

    private val rectWidth = size.createSize()[0].toInt()
    private val rectHeight = size.createSize()[1].toInt()

    private val firstRandomLocation = plane.randomLocation()
    private val secondRandomLocation = plane.randomLocation()
    private val thirdRandomLocation = plane.randomLocation()

    private val firstRect = makeRect(
        firstRandomLocation[0],
        firstRandomLocation[1],
        firstRandomLocation[0] + rectWidth,
        firstRandomLocation[1] + rectHeight,
    )

    private val secondRect = makeRect(
        secondRandomLocation[0],
        secondRandomLocation[1],
        secondRandomLocation[0] + rectWidth,
        secondRandomLocation[1] + rectHeight,
    )

    private val thirdRect = makeRect(
        thirdRandomLocation[0],
        thirdRandomLocation[1],
        thirdRandomLocation[0] + rectWidth,
        thirdRandomLocation[1] + rectHeight,
    )

    private val colorList = mutableListOf(Color.GRAY, Color.BLUE, Color.RED)

    @SuppressLint("DrawAllocation", "ResourceAsColor")
    override fun onDraw(canvas: Canvas?) {
        val firstPaint = Paint()
        val secondPaint = Paint()
        val thirdPaint = Paint()

        firstPaint.style = Paint.Style.FILL
        secondPaint.style = Paint.Style.FILL
        thirdPaint.style = Paint.Style.FILL

        firstPaint.color = colorList[0]
        secondPaint.color = colorList[1]
        thirdPaint.color = colorList[2]

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
                        contract.showInfo(true, colorList[0])
                    }
                    secondRect.contains(locationX!!, locationY!!) -> {
                        contract.showInfo(true, colorList[1])
                    }
                    thirdRect.contains(locationX!!, locationY!!) -> {
                        contract.showInfo(true, colorList[2])
                    }
                    else -> {
                        contract.showInfo(false, Color.WHITE)
                    }
                }
        }
        return flag
    }
}
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

    private var mainActivity = MainActivity()
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
    val alphaList = decideAlpha(mainActivity.seekBarProgress)

    private fun firstPaint(): Paint {
        val firstPaint = Paint()
        firstPaint.style = Paint.Style.FILL
        firstPaint.color = colorList[0]
        return firstPaint
    }

    private fun secondPaint(): Paint {
        val firstPaint = Paint()
        firstPaint.style = Paint.Style.FILL
        firstPaint.color = colorList[1]
        return firstPaint
    }

    private fun thirdPaint(): Paint {
        val firstPaint = Paint()
        firstPaint.style = Paint.Style.FILL
        firstPaint.color = colorList[2]
        return firstPaint
    }

    @SuppressLint("DrawAllocation", "ResourceAsColor")
    override fun onDraw(canvas: Canvas?) {
        canvas?.drawRect(firstRect, firstPaint())
        canvas?.drawRect(secondRect, secondPaint())
        canvas?.drawRect(thirdRect, thirdPaint())
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val locationX = event?.x?.toInt()
        val locationY = event?.y?.toInt()

        when (event?.action) {
            MotionEvent.ACTION_DOWN ->
                when {
                    firstRect.contains(locationX!!, locationY!!) -> {
                        contract.showInfo(true, firstPaint().color)
                    }
                    secondRect.contains(locationX!!, locationY!!) -> {
                        contract.showInfo(true, secondPaint().color)
                    }
                    thirdRect.contains(locationX!!, locationY!!) -> {
                        contract.showInfo(true, thirdPaint().color)
                    }
                    else -> {
                        contract.showInfo(false, Color.WHITE)
                    }
                }
        }
        return flag
    }

    override fun decideAlpha(alphaList: MutableList<Int>): MutableList<Int> {
        return alphaList
    }
}
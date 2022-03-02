package com.example.kotlin_drawingapp

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.kotlin_drawingapp.databinding.ViewCustomBinding

class CustomView : View {
    lateinit var check: Listener
    constructor(context: Context?, check: Listener) : super(context) {
        this.check = check
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    private var flag = false

    private lateinit var binding: ViewCustomBinding
    private val squareModelFactory = SquareModelFactory()

    private val rectWidth = squareModelFactory.createSquareObject()[1].toInt()
    private val rectHeight = squareModelFactory.createSquareObject()[2].toInt()

    private val firstLocationX = decideFirstRandomLocation()[0]
    private val firstLocationY = decideFirstRandomLocation()[1]

    private val secondLocationX = decideSecondRandomLocation()[0]
    private val secondLocationY = decideSecondRandomLocation()[1]

    private val thirdLocationX = decideThirdRandomLocation()[0]
    private val thirdLocationY = decideThirdRandomLocation()[1]

    private val firstRect = Rect(firstLocationX, firstLocationY, firstLocationX + rectWidth, firstLocationY + rectHeight)
    private val secondRect = Rect(secondLocationX, secondLocationY, secondLocationX + rectWidth, secondLocationY + rectHeight)
    private val thirdRect = Rect(thirdLocationX, thirdLocationY, thirdLocationX + rectWidth, thirdLocationY + rectHeight)

    private fun decideFirstRandomLocation(): Array<Int> {
        val location = arrayOf(0, 0)
        location[0] = (0..1500).random()
        location[1] = (0..1000).random()

        return location
    }

    private fun decideSecondRandomLocation(): Array<Int> {
        val location = arrayOf(0, 0)
        location[0] = (0..1500).random()
        location[1] = (0..1000).random()

        return location
    }

    private fun decideThirdRandomLocation(): Array<Int> {
        val location = arrayOf(0, 0)
        location[0] = (0..1500).random()
        location[1] = (0..1000).random()

        return location
    }

//     메서드로 Rect 을 만들 경우에는 MotionEvent.ACTION_DOWN 가 반응하지 않음
//    private fun drawCustomRect(): Rect {
//        val locationX = decideFirstRandomLocation()[0]
//        val locationY = decideFirstRandomLocation()[1]
//        val width = squareModelFactory.createSquareObject()[1].toInt()
//        val height = squareModelFactory.createSquareObject()[2].toInt()
//
//        return Rect(locationX, locationY, locationX + width, locationY + height)
//    }


    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        val paint = Paint()
        paint.style = Paint.Style.FILL
        paint.color = Color.GRAY
        super.onDraw(canvas)
        canvas?.drawRect(firstRect, paint)
        canvas?.drawRect(secondRect, paint)
        canvas?.drawRect(thirdRect, paint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val locationX = event?.x?.toInt()
        val locationY = event?.y?.toInt()

        when (event?.action) {
            MotionEvent.ACTION_DOWN ->
                when {
                    firstRect.contains(locationX!!, locationY!!) -> {
                        check.check(true)
                    }
                    secondRect.contains(locationX!!, locationY!!) -> {
                        check.check(true)
                    }
                    thirdRect.contains(locationX!!, locationY!!) -> {
                        check.check(true)
                    }
                    else -> {
                        check.check(false)
                    }
                }
        }
        return flag
    }
}
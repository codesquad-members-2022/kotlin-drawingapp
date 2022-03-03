package com.codesquad_han.kotlin_drawingapp.rectangle

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.codesquad_han.kotlin_drawingapp.model.Rectangle

class RectangleDrawingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var selectedRectangle: Rectangle? = null
    private var rectangleList = mutableListOf<Rectangle>()

    private var paint = Paint()

    override fun onDraw(canvas: Canvas) {
        rectangleList.forEach { rectangle ->
            paint.setColor(
                Color.argb(
                    rectangle.transparency.transparency * 255 / 10,
                    rectangle.backgroundColor.r,
                    rectangle.backgroundColor.g,
                    rectangle.backgroundColor.b
                )
            )
            canvas.drawRect(
                rectangle.point.x.toFloat(),
                rectangle.point.y.toFloat(),
                (rectangle.point.x + rectangle.size.width).toFloat(),
                (rectangle.point.y + rectangle.size.height).toFloat(),
                paint
            )

            Log.d("AppTest", "RectangleDrawingView/ ${rectangle.toString()}")
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        // 클릭 리스너 사용해서 액티비티 연결하기

        return true
    }

    fun drawRectangle(updatedRectangleList : MutableList<Rectangle>){
        rectangleList = updatedRectangleList
        invalidate()
    }




}
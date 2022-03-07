package View

import Presenter.Plane
import Data.Rectangle
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class DrawObjects(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val plane = Plane()
    private var rectangles: MutableList<Rectangle> = mutableListOf()
    private var rectangleStrokes: MutableList<Rectangle> = mutableListOf()
    fun makeRectangle() {
        rectangles = plane.getRandomRect()
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        rectangles.forEach { square ->
            canvas?.drawRect(square.rectangles, square.paint)
        }

        rectangleStrokes.forEach { stroke ->
            canvas?.drawRect(stroke.rectangles, stroke.paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event?.x
        val y = event?.y
        var foundRectangle: Boolean = false
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                rectangles.forEach { rectangle ->
                    foundRectangle = plane.isOnRectangle(x, y, rectangle)
                    if (foundRectangle) {
                        Log.d("rectangle right", "rectangle on the pic")
                        if(plane.rectangleStrokeList.contains(rectangle)) {
                            return@forEach
                        }
                        rectangleStrokes = plane.getRectangleStrokesList(foundRectangle, rectangle)
                        return@forEach
                    } else {
                        plane.setRectangleStrokeListEmpty()
                        rectangleStrokes.clear()
                    }
                }
                invalidate()
            }
//            MotionEvent.ACTION_UP -> {
//              TODO("커스텀리스너구현하기")
//            }
        }
        return true
    }
}
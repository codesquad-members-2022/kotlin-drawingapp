package view

import android.content.Context
import android.graphics.Canvas
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import android.view.WindowMetrics
import data.Rectangle

class TemporaryView(context: Context) : View(context) {

    private var rectangle: Rectangle? = null

    constructor(context: Context, rectangle: Rectangle?) : this(context) {
        this.rectangle = rectangle
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        this.rectangle?.draw(canvas)
    }

    fun getXY(x: Float?, y: Float?) {
        val displayMetrics = resources.displayMetrics
        val widthMetrics = displayMetrics.widthPixels * .6
        val heightMetrics = displayMetrics.heightPixels * .75

        var clickedX = x.let { x as Float }
        var clickedY = y.let { y as Float }

        if(clickedX >= widthMetrics) {
            clickedX = widthMetrics.toFloat()
        }
        if(clickedY <= 0f) {
            clickedY = 0f
        }
        if(clickedY >= heightMetrics) {
            clickedY = heightMetrics.toFloat()
        }

        val squareWidth = 250.toFloat()
        val squareHeight = 220.toFloat()
        rectangle?.rectangle?.left = clickedX
        rectangle?.rectangle?.top = clickedY
        rectangle?.rectangle?.right = clickedX + squareWidth
        rectangle?.rectangle?.bottom = clickedY + squareHeight
        rectangle?.paint?.alpha = 50
        invalidate()
    }
}
package view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import model.BackGroundColor
import model.Rect

class RectView(context: Context, val rect: Rect) : View(context) {

    private val left = rect.point.xPos.toFloat()
    private val right = (rect.point.xPos + rect.size.width).toFloat()
    private val top = rect.point.yPos.toFloat()
    private val bottom = (rect.point.yPos + rect.size.height).toFloat()
    private var backGroundColor = rect.backGroundColor.value?.getBackGroundColor()
    private var opacity = ((rect.opacity.value?.times(25.5))?.toInt())
    private var selectedFlag = false
    private val rectanglePaint = Paint().apply {
        this.style = Paint.Style.FILL

    }

    private val borderPaint = Paint().apply {
        this.style = Paint.Style.STROKE
        this.color = Color.BLACK
        this.strokeWidth = 2.0F
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        backGroundColor?.let {
            rectanglePaint.color = it
        }
        opacity?.let {
            rectanglePaint.alpha = it
        }
        canvas.drawRect(left, top, right, bottom, rectanglePaint)
        if (selectedFlag) {
            canvas.drawRect(left, top, right, bottom, borderPaint)
        }
    }

    fun drawBorder() {
        this.selectedFlag = true
        invalidate()
    }

    fun eraseBorder() {
        this.selectedFlag = false
        invalidate()
    }

    fun colorChange(color: BackGroundColor) {
        this.backGroundColor = color.getBackGroundColor()
        invalidate()
    }

    fun opacityChange(opacity: Int) {
        this.opacity = (opacity * 25.5).toInt()
        invalidate()
    }

}
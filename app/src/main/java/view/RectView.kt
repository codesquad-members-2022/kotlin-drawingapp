package view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.View
import model.BackGroundColor
import model.Rect

class RectView(context: Context) : View(context) {

    private lateinit var rectF:RectF
    lateinit var rect:Rect
    private var selectedFlag = false
    private val rectanglePaint = Paint()
    private val borderPaint = Paint().apply {
        this.style = Paint.Style.STROKE
        this.color = Color.BLACK
        this.strokeWidth = 2.0F
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(rectF, rectanglePaint)
        if (selectedFlag) {
            canvas.drawRect(rectF, borderPaint)
        }
    }

    fun drawRectangle():RectView{
        val left = rect.point.xPos.toFloat()
        val right = (rect.point.xPos + rect.size.width).toFloat()
        val top = rect.point.yPos.toFloat()
        val bottom = (rect.point.yPos + rect.size.height).toFloat()
        this.rectF = RectF(left,top,right,bottom)
        val backGroundColor = rect.backGroundColor.value?.getBackGroundColor()
        val opacity = ((rect.opacity.value?.times(25.5))?.toInt())
        backGroundColor?.let{
            this.rectanglePaint.color=it
        }
        opacity?.let{
            this.rectanglePaint.alpha=it
        }
        return this
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
        this.rectanglePaint.color = color.getBackGroundColor()
        invalidate()
    }

    fun opacityChange(opacity: Int) {
        this.rectanglePaint.alpha = (opacity * 25.5).toInt()
        invalidate()
    }

}
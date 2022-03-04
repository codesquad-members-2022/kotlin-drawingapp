package view

import android.content.Context
import android.graphics.*
import android.view.View
import model.BackGroundColor
import model.Photo
import model.Rect


class RectView(context: Context) : View(context) {

    private lateinit var rectF:RectF
    private var bitmap: Bitmap?= null
    lateinit var rect:Rect
    lateinit var photo: Photo
    private var selectedFlag = false
    private val rectanglePaint = Paint()
    private val borderPaint = Paint().apply {
        this.style = Paint.Style.STROKE
        this.color = Color.BLACK
        this.strokeWidth = 2.0F
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (selectedFlag) {
            canvas.drawRect(rectF, borderPaint)
        }
        bitmap?.let{
            canvas.drawBitmap(it, photo.point.xPos.toFloat(), photo.point.yPos.toFloat(), null)
            return
        }
        canvas.drawRect(rectF, rectanglePaint)

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

    fun drawPhoto(image: Bitmap):RectView{
        val left = photo.point.xPos.toFloat()
        val right = (photo.point.xPos + photo.size.width).toFloat()
        val top = photo.point.yPos.toFloat()
        val bottom = (photo.point.yPos + photo.size.height).toFloat()
        this.rectF = RectF(left,top,right,bottom)
        this.rectanglePaint.isFilterBitmap=true
        this.bitmap= resizeBitmap(image)
        return this
    }

    private fun resizeBitmap(image: Bitmap):Bitmap{
        val width =  photo.size.width // 축소시킬 너비
        val height = photo.size.height // 축소시킬 높이
        var bmpWidth = image.width.toFloat()
        var bmpHeight = image.height.toFloat()

        if (bmpWidth > width) {
            // 원하는 너비보다 클 경우의 설정
            val mWidth = bmpWidth / 100
            val scale = width / mWidth
            bmpWidth *= scale / 100
            bmpHeight *= scale / 100
        } else if (bmpHeight > height) {
            // 원하는 높이보다 클 경우의 설정
            val mHeight = bmpHeight / 100
            val scale = height / mHeight
            bmpWidth *= scale / 100
            bmpHeight *= scale / 100
        }
        return Bitmap.createScaledBitmap(
            image,
            bmpWidth.toInt(), bmpHeight.toInt(), true
        )
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
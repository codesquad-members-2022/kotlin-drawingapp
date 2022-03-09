package view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import model.BackGroundColor
import model.Photo
import model.Rect


class RectView(context: Context) : View(context) {
    var bitmap: Bitmap? = null
    var rectId = ""
    var photoId = ""
    var left = 0.0F
    private var right = 0.0F
    var top = 0.0F
    private var bottom = 0.0F
    var rectWidth = 0
    var rectHeight = 0
    var alpha = 0
    var backGroundRGB: String = ""
    var selectedFlag = false
    var rectanglePaint = Paint()
    private val borderPaint = Paint().apply {
        this.style = Paint.Style.STROKE
        this.color = Color.BLACK
        this.strokeWidth = 2.0F
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (selectedFlag) {
            canvas.drawRect(left, top, right, bottom, borderPaint)
        }
        bitmap?.let {
            val paint = Paint()
            paint.alpha = this.alpha
            canvas.drawBitmap(it, left, top, paint)
            return
        }
        rectanglePaint.alpha = this.alpha
        canvas.drawRect(left, top, right, bottom, rectanglePaint)

    }

    fun drawRectangle(rect: Rect) {
        rect.let {
            rectId = rect.rectId
            left = it.point.xPos.toFloat()
            right = (it.point.xPos + it.size.width).toFloat()
            top = it.point.yPos.toFloat()
            this.rectWidth = it.size.width
            this.rectHeight = it.size.height
            bottom = (it.point.yPos + it.size.height).toFloat()
            this.backGroundRGB = it.backGroundColor.value?.getRGBHexValue().toString()
            val backGroundColor = it.backGroundColor.value?.getBackGroundColor()
            val opacity = ((it.opacity.value?.times(25.5))?.toInt())
            backGroundColor?.let { color ->
                this.rectanglePaint.color = color

            }
            opacity?.let { op ->
                this.alpha = op
            }
        }
    }

    fun drawPhoto(image: Bitmap, photo: Photo) {
        photo.let {
            rectId = photo.rectId
            photoId = photo.photoId
            left = it.point.xPos.toFloat()
            right = (it.point.xPos + it.size.width).toFloat()
            top = it.point.yPos.toFloat()
            bottom = (it.point.yPos + it.size.height).toFloat()
            it.opacity.value?.let { alpha ->
                this.alpha = (alpha * 25.5).toInt()
            }
        }
        this.backGroundRGB = "No Color"
        this.bitmap = resizeBitmap(image, photo)
    }

    private fun resizeBitmap(image: Bitmap, photo: Photo): Bitmap {
        val width = photo.size.width // 축소시킬 너비
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

    fun changeColor(color: BackGroundColor) {
        this.rectanglePaint.color = color.getBackGroundColor()
        invalidate()
    }

    fun changeOpacity(opacity: Int) {
        this.alpha = (opacity * 25.5).toInt()
        invalidate()
    }

    fun onTouch(event: MotionEvent): RectView {
        val x: Float
        val y: Float
        var tempView = RectView(context)
        tempView.rectId = "temp"
        if(this.photoId==""){
            tempView.rectanglePaint.color = this.rectanglePaint.color
        }
        else{
            tempView.photoId= "tempPhoto"
            tempView.bitmap = this.bitmap
        }

        tempView.alpha = (5 * 25.5).toInt()
        when (event.action) {
            MotionEvent.ACTION_MOVE -> {
                x = event.getX(0)
                y = event.getY(0)
                tempView.left = x
                tempView.top = y
                tempView.right = (x + this.rectWidth)
                tempView.bottom = y + this.rectHeight
                return tempView
            }
            MotionEvent.ACTION_UP -> {
                x = event.getX(0)
                y = event.getY(0)
                this.left = x
                this.top = y
                this.right = x + this.rectWidth
                this.bottom = y + this.rectHeight
                return this
            }
        }
        return this
    }


}
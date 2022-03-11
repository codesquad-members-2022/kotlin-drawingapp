package view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
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
            it.point.value?.let {point->
                left = point.xPos.toFloat()
                top = point.yPos.toFloat()

            }
            it.size.value?.let{size->
                right = (this.left + size.width)
                bottom = (this.top + size.height)
                this.rectWidth = size.width
                this.rectHeight = size.height
            }
            this.rectId= it.rectId
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
            it.point.value?.let {point->
                left = point.xPos?.toFloat()
                top = point.yPos.toFloat()

            }
            it.size.value?.let{ size ->
                right = (this.left + size.width)
                bottom = (this.top + size.height)
                this.rectWidth = size.width
                this.rectHeight = size.height
            }
            it.opacity.value?.let { alpha ->
                this.alpha = (alpha * 25.5).toInt()
            }
        }
        this.backGroundRGB = "No Color"
        this.bitmap = resizeBitmap(image, photo)
    }

    private fun resizeBitmap(image: Bitmap, photo: Photo): Bitmap {
        val width = this.rectWidth // 축소시킬 너비
        val height = this.rectHeight // 축소시킬 높이
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

    fun onTouch(event: MotionEvent,tempView:RectView) {
        val x: Float
        val y: Float
        when (event.action) {
            MotionEvent.ACTION_MOVE -> {
                x = event.getX(0)
                y = event.getY(0)
                tempView.left = x
                tempView.top = y
                tempView.right = (x + this.rectWidth)
                tempView.bottom = y + this.rectHeight
                
            }
            MotionEvent.ACTION_UP -> {
                x = event.getX(0)
                y = event.getY(0)
                this.left = x
                this.top = y
                this.right = x + this.rectWidth
                this.bottom = y + this.rectHeight
            }
        }
    }


    fun changeSize(width:Int, height:Int){
        Log.d("Test", "${width},${height}")
        this.right= this.left+ width
        this.bottom= this.top+ height
        this.rectHeight= height
        this.rectWidth= width
        invalidate()
    }

    fun changePos(xPos:Float, yPos:Float){
        this.left= xPos
        this.right= this.left+ this.rectWidth
        this.top= yPos
        this.bottom= this.top + this.rectHeight
        invalidate()
    }


}
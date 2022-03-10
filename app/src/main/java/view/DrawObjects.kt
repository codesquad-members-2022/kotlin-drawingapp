package view

import data.Rectangle
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import presenter.Contract
import presenter.Presenter

class DrawObjects(context: Context, attributeSet: AttributeSet) :
    ConstraintLayout(context, attributeSet), Contract.CustomView {
    private var rectangle: Rectangle? = null
    private var presenter: Contract.Presenter = Presenter()
    private var rectangles: MutableList<Rectangle> = mutableListOf()
    private var rectangleStrokes: MutableList<Rectangle> = mutableListOf()
    private var bitmapImages: MutableList<Bitmap> = mutableListOf()
    var customListener: CustomListener? = null
    private val TAG = "CustomView"

    fun countRectangles(): String {
        return rectangles.count().toString()
    }

    fun makeRectangle() {
        rectangles = presenter.getRectangles()
        invalidate()
    }

    fun removeRectangleAndStrokes() {
        rectangles.clear()
        rectangleStrokes.clear()
        invalidate()
    }

    fun changeRectangleColor() {
        if(rectangle != null) {
            val nonNullRectangle = rectangle as Rectangle
            rectangles = presenter.setRectangleColor(nonNullRectangle)!!
            invalidate()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.d(TAG, "onAttachedWindow")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.d(TAG, "onMeasure")
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.d(TAG, "onLayout")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d(TAG, "onDraw")
        drawRectangle(canvas, rectangles)
        drawRectangleStroke(canvas, rectangleStrokes)
        drawImages(bitmapImages)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event?.x
        val y = event?.y
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                updateRectangleStrokeList(x, y, rectangles)
                invalidate()
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                return false
            }
            MotionEvent.ACTION_UP -> {
                return false
            }
        }
        return false
    }

    private fun updateRectangleStrokeList(
        x: Float?,
        y: Float?,
        rectangles: MutableList<Rectangle>
    ) {
        rectangles.forEach { rectangle ->
            if (presenter.isRectangle(x, y, rectangle)) {
                rectangleStrokes = presenter.getStrokes(rectangle)
                getRectangleColor(rectangle)
                return
            }
        }
        rectangleStrokes.clear()
    }

    private fun getRectangleColor(rectangle: Rectangle) {
        this.rectangle = rectangle
        customListener?.isClicked("#${Integer.toHexString(rectangle.paint.color)}")
    }

    override fun drawRectangle(canvas: Canvas?, rectangles: MutableList<Rectangle>) {
        rectangles.forEach { square ->
            canvas?.drawRect(square.rectangles, square.paint)
        }
    }

    override fun drawRectangleStroke(
        canvas: Canvas?,
        rectanglesStrokesList: MutableList<Rectangle>
    ) {
        rectanglesStrokesList.forEach { stroke ->
            canvas?.drawRect(stroke.rectangles, stroke.paint)
        }
    }

    fun loadImages(bitmapImages: MutableList<Bitmap>) {
        this.bitmapImages = bitmapImages
    }

    fun drawImages(bitmapImages: MutableList<Bitmap>) {
        if(bitmapImages.count() != 0) {
            var imageView = ImageView(context)
            imageView.setImageBitmap(bitmapImages[0])
            var constraintParam = LayoutParams(
                150,
                150
            )
            constraintParam.topToTop = LayoutParams.PARENT_ID
            constraintParam.bottomToBottom = LayoutParams.PARENT_ID
            constraintParam.leftMargin = 800
            var image = (this.parent) as ConstraintLayout
            image.addView(imageView, constraintParam)
        }
    }
}
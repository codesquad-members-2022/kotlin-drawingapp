package view

import data.RegularRectangle
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import data.ImageRectangle
import data.Rectangle
import data.StrokeRectangle
import presenter.Contract
import presenter.Presenter

class DrawObjects(context: Context, attributeSet: AttributeSet) :
    View(context, attributeSet), Contract.CustomView {
    private var rectangle: RegularRectangle? = null
    private var imageRectangle: ImageRectangle? = null
    private var recentlyClickedObject: Rectangle? = null
    private var presenter: Contract.Presenter = Presenter()
    private var rectangles: MutableList<RegularRectangle> = mutableListOf()
    private var rectangleStrokes: MutableList<StrokeRectangle> = mutableListOf()
    private var bitmapImages: MutableList<Bitmap> = mutableListOf()
    private var bitmapRectangles: MutableList<ImageRectangle> = mutableListOf()
    private lateinit var temporaryView: TemporaryView
    private val defaultAlphaValue = 220

    var customListener: CustomListener? = null
    var locationListener: WidthAndHeightListener? = null
    private val TAG = "DrawObject"

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d(TAG, "onDraw")
        drawAll(canvas)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event?.x
        val y = event?.y
        temporaryView = TemporaryView(context, recentlyClickedObject)

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                updateRectangleStrokeList(x, y)
                invalidate()
                return true
            }
            MotionEvent.ACTION_MOVE -> {

                presenter.removeStrokes()
                moveRectanglesOnTemporaryView(x, y)
                return true
            }
            MotionEvent.ACTION_UP -> {
                // alpha value is personally decided
                recentlyClickedObject?.paint?.alpha = defaultAlphaValue
                invalidate()

                return false
            }
        }
        return false
    }

    /* Helper Function */

    override fun drawAll(canvas: Canvas?) {
        val unifiedRectangles = mutableListOf<Rectangle>()
        unifiedRectangles.addAll(rectangles)
        unifiedRectangles.addAll(rectangleStrokes)
        unifiedRectangles.addAll(bitmapRectangles)
        unifiedRectangles.forEach { drawingObjects ->
            drawingObjects.draw(canvas)
        }
    }

    private fun updateRectangleStrokeList(
        x: Float?,
        y: Float?,
    ) {
        if (presenter.isRectangle(x, y)) {
            presenter.makeStrokes()
            rectangleStrokes = presenter.getStrokes()
            getRecentRectangleColor()
            return
        }
        presenter.removeStrokes()
    }

    private fun moveRectanglesOnTemporaryView(
        x: Float?,
        y: Float?,
    ) {
        invalidate()
        temporaryView.getXY(x, y)
        showRectangleWidthAndHeight()
    }

    private fun showRectangleWidthAndHeight() {
        locationListener?.showWidthAndHeight(
            "${recentlyClickedObject?.rectangle?.left}, " +
                    "${recentlyClickedObject?.rectangle?.top}, " +
                    "${recentlyClickedObject?.rectangle?.right}, " +
                    "${recentlyClickedObject?.rectangle?.bottom}"
        )
    }

    fun countRectangles(): String {
        return rectangles.count().toString()
    }

    fun makeRectangle() {
        rectangles = presenter.getRectangles()
        invalidate()
    }

    fun removeRectangleAndStrokes() {
        rectangle = null
        rectangles.clear()
        bitmapImages.clear()
        bitmapRectangles.clear()
        presenter.removeStrokes()
        invalidate()
    }

    fun changeRectangleColor() {
        rectangle.let { rectangle ->
            if (rectangle?.paint != null) {
                val nonNullRectangle = rectangle
                rectangles = presenter.changeRectangleColor(nonNullRectangle)!!
                invalidate()
            }
        }
    }

    private fun getRecentClickedRectangles() {
        this.rectangle = presenter.getRecentClickedRectangle()
        this.imageRectangle = presenter.getRecentClickedImageRectangle()
        this.recentlyClickedObject = presenter.getRecentlyClickedObject()
    }

    private fun getRecentRectangleColor() {
        getRecentClickedRectangles()
        if (this.rectangle == null) {
            customListener?.isClicked("NULL")
            return
        }
        customListener?.isClicked("#${this.rectangle?.paint.let { Integer.toHexString(it!!.color) }}")
    }

    fun saveAndGetImage(bitmapImages: MutableList<Bitmap>) {
        if (bitmapImages.count() != 0) {
            presenter.makeImageRectangle(bitmapImages[bitmapImages.count() - 1])
            println("imageRectangle : DrawObjects ${bitmapImages.count()}")
        }
        loadImageRectangles()
    }

    private fun loadImageRectangles() {
        bitmapRectangles = presenter.getImageRectangle()
    }

}
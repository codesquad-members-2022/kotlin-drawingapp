package com.codesquard.kotlin_drawingapp

import android.content.Context
import android.graphics.Color
import android.util.DisplayMetrics
import android.view.View


class RectangleViewModelFactory(val mainActivity: Context) {
    private val rectangleView = RectangleViewModel()

    private fun setRectangleViewID() {
        val letter = "abcdefghijklmnopqrstuvwxyz0123456789"
        var randomID = ""
        (1..9).forEach {
            randomID += letter.random()
            if (it == 3 || it == 6)
                randomID += "-"
        }
        rectangleView.setID(randomID)
    }

    private fun setRectangleViewPoint() {
        val pointX = (0..1000).random()
        val pointY = (0..1000).random()
        rectangleView.setPoint(pointX, pointY)
    }

    private fun dp2px(dp: Float): Float {
        val resources = mainActivity.resources
        val metrics = resources.displayMetrics
        return dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    private fun setRectangleViewSize() {
        val width = dp2px(150f)
        val height = dp2px(120f)
        rectangleView.setSize(width.toInt(), height.toInt())
    }

    private fun setRectangleViewColor() {
        val r = (0..255).random()
        val g = (0..255).random()
        val b = (0..255).random()
        rectangleView.setColor(r, g, b)
    }

    private fun setRectangleViewAlpha() {
        val alpha = (1..10).random()
        rectangleView.setAlpha(alpha)
    }

    fun createView(view: View) {
        setRectangleViewID()
        setRectangleViewPoint()
        setRectangleViewColor()
        setRectangleViewAlpha()
        setRectangleViewSize()
        view.setBackgroundColor(
            Color.rgb(
                rectangleView.getColor()[0],
                rectangleView.getColor()[1],
                rectangleView.getColor()[2]
            )
        )
        view.alpha = rectangleView.getAlpha()
        view.x = rectangleView.getPoint()[0].toFloat()
        view.y = rectangleView.getPoint()[1].toFloat()
        view.layoutParams.width = rectangleView.getSize()[0]
        view.layoutParams.height = rectangleView.getSize()[1]
    }

}

class RectangleViewModel {
    private lateinit var id: String
    private lateinit var point: Array<Int>
    private lateinit var size: Array<Int>
    private lateinit var color: Array<Int>
    private var alpha = 0f

    fun setPoint(x: Int, y: Int) {
        point = arrayOf(x, y)
    }

    fun getPoint() = point

    fun setSize(width: Int, height: Int) {
        size = arrayOf(width, height)
    }

    fun getSize() = size

    fun setColor(r: Int, g: Int, b: Int) {
        color = arrayOf(r, g, b)
    }

    fun getColor() = color

    fun setAlpha(alpha: Int) {
        if (alpha in 1..10)
            this.alpha = alpha.toFloat()
    }

    fun getAlpha() = alpha

    fun setID(id: String) {
        this.id = id
    }

    fun getID() = id

    override fun toString(): String {
        return "(${id}), X:${point[0]}, Y:${point[0]}, W${size[0]}, H${size[1]}, R:${color[0]}, G:${color[1]}, B:${color[2]}, Alpha: $alpha"
    }
}
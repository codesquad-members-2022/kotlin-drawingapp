package com.codesquard.kotlin_drawingapp

import android.graphics.Color
import android.view.View

class RectangleViewModelFactory {
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

    fun setRectangleViewPoint(pointX: Int, pointY: Int) {
        rectangleView.setPoint(pointX, pointY)
    }

    fun setRectangleViewColor(r: UByte, g: UByte, b: UByte) {
        rectangleView.setColor(r, g, b)
    }

    fun setRectangleViewAlpha(alpha: Int) {
        rectangleView.setAlpha(alpha)
    }

    fun createView(view: View) {
        view.setBackgroundColor(
            Color.rgb(
                rectangleView.getColor()[0].toInt(),
                rectangleView.getColor()[1].toInt(),
                rectangleView.getColor()[2].toInt()
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
    private val size = arrayOf(150, 120)
    private lateinit var color: Array<UByte>
    private var alpha = 0f

    fun setPoint(x: Int, y: Int) {
        point = arrayOf(x, y)
    }

    fun getPoint() = point

    fun getSize() = size

    fun setColor(r: UByte, g: UByte, b: UByte) {
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
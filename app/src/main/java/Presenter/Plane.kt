package Presenter

import Data.RectFactory
import Data.Rectangle

class Plane {
    lateinit var factory: GenerateRectangle
    var rectangleList = mutableListOf<Rectangle>()
    var rectangleStrokeList = mutableListOf<Rectangle>()

    fun getRandomRect(): MutableList<Rectangle> {
        factory = RectFactory()
        rectangleList.add(factory.makeRandomRect())
        return rectangleList
    }

    fun getRectNumber(): Int {
        return rectangleList.count()
    }

    fun isOnRectangle(x: Float?, y: Float?, rectangle: Rectangle): Boolean {
        val bottom = rectangle.rectangles.bottom
        val left = rectangle.rectangles.left
        val right = rectangle.rectangles.right
        val top = rectangle.rectangles.top
        val clickX: Float
        val clickY: Float
        if (x == null || y == null) {
            clickX = 0F
            clickY = 0F
        } else {
            clickX = x
            clickY = y
        }
        if ((clickX in left..right) && (clickY in top..bottom)) {
            return true
        }
        return false
    }

    fun getRectangleStrokesList(
        onRectangle: Boolean,
        rectangle: Rectangle
    ): MutableList<Rectangle> {
        val stroke = RectFactory()
        if (onRectangle) {
            val strokeRect = stroke.makeRectangleStrokesList(rectangle)
            rectangleStrokeList.add(strokeRect)
        }
        return rectangleStrokeList
    }

    fun setRectangleStrokeListEmpty() {
        rectangleStrokeList.clear()
    }
}
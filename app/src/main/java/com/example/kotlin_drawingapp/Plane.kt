package com.example.kotlin_drawingapp

import com.example.kotlin_drawingapp.model.Rectangle
import com.example.kotlin_drawingapp.model.RectangleFactory

class Plane {
    private val rectangleList = mutableListOf<Rectangle>()

    fun getRectangleCount(): Int {
        return rectangleList.size
    }

    fun createRectangle(): Rectangle {
        val rect = RectangleFactory.create()
        rectangleList.add(rect)
        return rect
    }

    fun modifyRectangle(index: Int, rectangle: Rectangle) {
        rectangleList[index] = rectangle
    }

    fun getRectangleByIndex(index: Int): Rectangle {
        return rectangleList[index]
    }

    fun getRectangleByPosition(x: Int, y: Int): Rectangle? {
        for (index in rectangleList.size-1 downTo 0) {
            if (rectangleList[index].point.x >= x && rectangleList[index].point.x + x <= x &&
                rectangleList[index].point.y >= y && rectangleList[index].point.y + y <= y
            ) {
                return rectangleList[index]
            }
        }

        return null
    }
}

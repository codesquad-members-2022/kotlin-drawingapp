package com.example.kotlin_drawingapp.model.source

import com.example.kotlin_drawingapp.model.Rectangle

class DefaultPlaneRepository : PlaneRepository {
    private val rectangleList = mutableListOf<Rectangle>()
    private var currentSelectedRectangle: Rectangle? = null

    override fun getCurrentSelectedRectangle(): Rectangle? {
        return currentSelectedRectangle
    }

    override fun getRectangleCount(): Int {
        return rectangleList.size
    }

    override fun saveCurrentSelectedRectangle(rectangle: Rectangle?) {
        currentSelectedRectangle = rectangle
    }

    override fun saveRectangle(rectangle: Rectangle) {
        rectangleList.add(rectangle)
    }

    override fun saveSelectedStatus(rectangle: Rectangle, selected: Boolean) {
        for (rect in rectangleList) {
            if (rect == rectangle) {
                rect.selected = selected
                return
            }
        }
    }

    override fun clearRectangleBorder() {
        for (rectangle in rectangleList) {
            rectangle.selected = false
        }
    }

    override fun getAllRectangles(): List<Rectangle> {
        return rectangleList.toList()
    }

    override fun modifyRectangle(target: Rectangle, replacement: Rectangle) {
        for (index in 0 until rectangleList.size) {
            if (rectangleList[index] == target) {
                rectangleList[index] = replacement
                return
            }
        }
    }

    override fun getRectangleByIndex(index: Int): Rectangle {
        return rectangleList[index]
    }

    override fun getRectangleByPosition(x: Int, y: Int): Rectangle? {
        for (index in rectangleList.size-1 downTo 0) {
            if (rectangleList[index].point.x <= x && rectangleList[index].point.x + rectangleList[index].size.width >= x &&
                rectangleList[index].point.y <= y && rectangleList[index].point.y + rectangleList[index].size.height >= y
            ) {
                return rectangleList[index]
            }
        }

        return null
    }
}

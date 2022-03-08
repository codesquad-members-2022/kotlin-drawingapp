package com.example.kotlin_drawingapp.model.source.memory

import com.example.kotlin_drawingapp.model.Rectangle
import com.example.kotlin_drawingapp.model.source.DrawingDataSource

class PlaneDataSource : DrawingDataSource {
    private val planeDb = Plane()

    override fun getRectangleCount(): Int {
        return planeDb.getRectangleCount()
    }

    override fun getCurrentSelectedRectangle(): Rectangle? {
        // Repository에 구현되어 있음
        return null
    }

    override fun getAllRectangles(): List<Rectangle> {
        return planeDb.getAllRectangles()
    }

    override fun getRectangleByIndex(index: Int): Rectangle {
        return planeDb.getRectangleByIndex(index)
    }

    override fun getRectangleByPosition(x: Int, y: Int): Rectangle? {
        return planeDb.getRectangleByPosition(x, y)
    }

    override fun saveCurrentSelectedRectangle(rectangle: Rectangle?) {
        // Repository에 구현되어 있음
    }

    override fun saveRectangle(rectangle: Rectangle) {
        planeDb.saveRectangle(rectangle)
    }

    override fun saveSelectedStatus(rectangle: Rectangle, selected: Boolean) {
        planeDb.saveSelectedStatus(rectangle, selected)
    }

    override fun modifyRectangle(target: Rectangle, replacement: Rectangle) {
        planeDb.modifyRectangle(target, replacement)
    }

    override fun clearRectangleBorder() {
        planeDb.clearRectangleBorder()
    }
}

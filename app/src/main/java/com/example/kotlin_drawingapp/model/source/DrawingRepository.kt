package com.example.kotlin_drawingapp.model.source

import com.example.kotlin_drawingapp.model.Rectangle

class DrawingRepository(private val drawingDataSource: DrawingDataSource) : DrawingDataSource {
    private var currentSelectedRectangle: Rectangle? = null

    override fun getRectangleCount(): Int {
        return drawingDataSource.getRectangleCount()
    }

    override fun getAllRectangles(): List<Rectangle> {
        return drawingDataSource.getAllRectangles()
    }

    override fun getRectangleByIndex(index: Int): Rectangle {
        if (index < 0 || index >= getRectangleCount()) {
            throw Exception("범위에서 벗어났습니다. (0~${getRectangleCount()})")
        }

        return drawingDataSource.getRectangleByIndex(index)
    }

    override fun getRectangleByPosition(x: Int, y: Int): Rectangle? {
        return drawingDataSource.getRectangleByPosition(x, y)
    }

    override fun getCurrentSelectedRectangle(): Rectangle? {
        return currentSelectedRectangle
    }

    override fun saveCurrentSelectedRectangle(rectangle: Rectangle?) {
        currentSelectedRectangle = rectangle
    }

    override fun saveRectangle(rectangle: Rectangle) {
        drawingDataSource.saveRectangle(rectangle)
    }

    override fun saveSelectedStatus(rectangle: Rectangle, selected: Boolean) {
        drawingDataSource.saveSelectedStatus(rectangle, selected)
    }

    override fun modifyRectangle(target: Rectangle, replacement: Rectangle) {
        drawingDataSource.modifyRectangle(target, replacement)
    }

    override fun clearRectangleBorder() {
        drawingDataSource.clearRectangleBorder()
    }
}

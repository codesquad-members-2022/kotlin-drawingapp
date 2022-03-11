package com.example.kotlin_drawingapp.model.source

import com.example.kotlin_drawingapp.model.draw.DrawObject

class DrawingRepository(private val drawingDataSource: DrawingDataSource) : DrawingDataSource {
    private var currentSelectedRectangle: DrawObject? = null

    override fun getDrawObjectCount(): Int {
        return drawingDataSource.getDrawObjectCount()
    }

    override fun getAllDrawObject(): List<DrawObject> {
        return drawingDataSource.getAllDrawObject()
    }

    override fun getDrawObjectByIndex(index: Int): DrawObject {
        if (index < 0 || index >= getDrawObjectCount()) {
            throw Exception("범위에서 벗어났습니다. (0~${getDrawObjectCount()})")
        }

        return drawingDataSource.getDrawObjectByIndex(index)
    }

    override fun getDrawObjectByPosition(x: Int, y: Int): DrawObject? {
        return drawingDataSource.getDrawObjectByPosition(x, y)
    }

    override fun getCurrentSelectedDrawObject(): DrawObject? {
        return currentSelectedRectangle
    }

    override fun saveCurrentSelectedDrawObject(drawObject: DrawObject?) {
        currentSelectedRectangle = drawObject
    }

    override fun saveDrawObject(drawObject: DrawObject) {
        drawingDataSource.saveDrawObject(drawObject)
    }

    override fun saveSelectedStatus(drawObject: DrawObject, selected: Boolean) {
        drawingDataSource.saveSelectedStatus(drawObject, selected)
    }

    override fun modifyDrawObject(target: DrawObject, replacement: DrawObject) {
        drawingDataSource.modifyDrawObject(target, replacement)
    }

    override fun clearDrawObjectBorder() {
        drawingDataSource.clearDrawObjectBorder()
    }
}

package com.example.kotlin_drawingapp.model.source.memory

import com.example.kotlin_drawingapp.model.draw.DrawObject
import com.example.kotlin_drawingapp.model.source.DrawingDataSource

class PlaneDataSource : DrawingDataSource {
    private val planeDb = Plane()

    override fun getDrawObjectCount(): Int {
        return planeDb.getDrawObjectCount()
    }

    override fun getCurrentSelectedDrawObject(): DrawObject? {
        // Repository에 구현되어 있음
        return null
    }

    override fun getAllDrawObject(): List<DrawObject> {
        return planeDb.getAllDrawObject()
    }

    override fun getDrawObjectByIndex(index: Int): DrawObject {
        return planeDb.getDrawObjectByIndex(index)
    }

    override fun getDrawObjectByPosition(x: Int, y: Int): DrawObject? {
        return planeDb.getDrawObjectByPosition(x, y)
    }

    override fun saveCurrentSelectedDrawObject(drawObject: DrawObject?) {
        // Repository에 구현되어 있음
    }

    override fun saveDrawObject(drawObject: DrawObject) {
        planeDb.saveDrawObject(drawObject)
    }

    override fun saveSelectedStatus(drawObject: DrawObject, selected: Boolean) {
        planeDb.saveSelectedStatus(drawObject, selected)
    }

    override fun modifyDrawObject(target: DrawObject, replacement: DrawObject) {
        planeDb.modifyDrawObject(target, replacement)
    }

    override fun clearDrawObjectBorder() {
        planeDb.clearDrawObjectBorder()
    }
}

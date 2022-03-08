package com.example.kotlin_drawingapp.model.source

import com.example.kotlin_drawingapp.model.Rectangle

interface PlaneRepository {

    fun getRectangleCount(): Int

    fun getAllRectangles(): List<Rectangle>

    fun getRectangleByIndex(index: Int): Rectangle

    fun getRectangleByPosition(x: Int, y: Int): Rectangle?

    fun getCurrentSelectedRectangle(): Rectangle?

    fun saveCurrentSelectedRectangle(rectangle: Rectangle?)

    fun saveRectangle(rectangle: Rectangle)

    fun saveSelectedStatus(rectangle: Rectangle, selected: Boolean)

    fun modifyRectangle(target: Rectangle, replacement: Rectangle)

    fun clearRectangleBorder()
}
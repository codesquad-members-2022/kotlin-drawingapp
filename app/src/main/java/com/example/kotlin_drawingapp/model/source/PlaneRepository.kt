package com.example.kotlin_drawingapp.model.source

import com.example.kotlin_drawingapp.model.Rectangle
import com.example.kotlin_drawingapp.model.RectangleBorder

interface PlaneRepository {

    fun getRectangleCount(): Int

    fun getAllRectangles(): List<Rectangle>

    fun getAllRectangleBorders(): List<RectangleBorder>

    fun getRectangleByIndex(index: Int): Rectangle

    fun getRectangleByPosition(x: Int, y: Int): Rectangle?

    fun getCurrentSelectedRectangle(): Rectangle?

    fun saveCurrentSelectedRectangle(rectangle: Rectangle?)

    fun saveRectangleBorder(border: RectangleBorder)

    fun saveRectangle(rectangle: Rectangle)

    fun modifyRectangle(target: Rectangle, replacement: Rectangle)

    fun clearRectangleBorder()
}
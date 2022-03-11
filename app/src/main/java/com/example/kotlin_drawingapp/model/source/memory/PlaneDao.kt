package com.example.kotlin_drawingapp.model.source.memory

import com.example.kotlin_drawingapp.model.draw.DrawObject

interface PlaneDao {

    fun getDrawObjectCount(): Int

    fun getAllDrawObject(): List<DrawObject>

    fun getDrawObjectByIndex(index: Int): DrawObject

    fun getDrawObjectByPosition(x: Int, y: Int): DrawObject?

    fun saveDrawObject(drawObject: DrawObject)

    fun saveSelectedStatus(drawObject: DrawObject, selected: Boolean)

    fun modifyDrawObject(target: DrawObject, replacement: DrawObject)

    fun clearDrawObjectBorder()
}
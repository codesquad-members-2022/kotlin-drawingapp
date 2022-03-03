package com.codesquad_han.kotlin_drawingapp.data

import com.codesquad_han.kotlin_drawingapp.model.Plane
import com.codesquad_han.kotlin_drawingapp.model.Rectangle

class RectangleRepositoryImpl(var plane: Plane) : RectangleRepository {
    override fun addRectangle() {
        plane.generateRectangle()
    }

    override fun getRectangleList() = plane.returnRectangleList()
}
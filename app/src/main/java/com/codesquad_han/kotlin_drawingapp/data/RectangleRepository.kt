package com.codesquad_han.kotlin_drawingapp.data

import com.codesquad_han.kotlin_drawingapp.model.Rectangle

interface RectangleRepository {

    fun addRectangle()

    fun getRectangleList() : MutableList<Rectangle>
}
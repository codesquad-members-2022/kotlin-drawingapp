package com.codesquard.kotlin_drawingapp.presenter

import com.codesquard.kotlin_drawingapp.model.Rectangle

interface RectangleListener {

    fun onCreateRectangle(newRect: Rectangle)

    fun onSelectRectangle(index: Int = -1)

    fun onUpdateRectangle()

    fun onDragRectangle(tempRect: Rectangle?)

}
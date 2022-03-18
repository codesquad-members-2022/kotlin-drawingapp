package com.codesquard.kotlin_drawingapp.presenter

import com.codesquard.kotlin_drawingapp.model.Rectangle

interface RectangleListener {

    fun onCreateRectangle(newRect: Rectangle)

    fun onSelectRectangle(selectedRect: Rectangle)

    fun onUnSelectRectangle()

    fun onUpdateRectangle()

    fun onDragRectangle(tempRect: Rectangle?)

    fun onMeasureTextSize(textRect: Rectangle)

}
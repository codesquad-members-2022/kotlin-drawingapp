package com.codesquard.kotlin_drawingapp

interface RectangleListener {

    fun onCreateRectangle(newRect: Rectangle)

    fun onSelectRectangle(index: Int)

    fun onSelectNoRectangle()

}
package com.codesquard.kotlin_drawingapp

class RectangleModel(private val listener: RectangleListener) {
    private val rectangleFactory = RectangleFactory()
    private val rectangleList = mutableListOf<Rectangle>()

    fun createNewRectangle() {
        rectangleList.add(rectangleFactory.getInstance())
        listener.onCreateRectangle(rectangleList[rectangleList.size - 1])
    }
}
package com.codesquad_han.kotlin_drawingapp.model

class Plane(rectangleFactory: RectangleFactory) {
    private var rectangleList = mutableListOf<Rectangle>()
    private var rectangleFactory = rectangleFactory

    fun generateRectangle() {
        val rectangle = rectangleFactory.generateRectangle()
        rectangleList.add(rectangle)
    }

    fun returnRectangleList() = rectangleList
}
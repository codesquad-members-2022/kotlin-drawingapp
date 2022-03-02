package com.codesquad_han.kotlin_drawingapp.model

class Plane(rectangleFactory: RectangleFactory) {
    private var rectangleArrayList = ArrayList<Rectangle>()
    private var rectangleFactory = rectangleFactory

    fun generateRectangle() : Rectangle{
        val rectangle = rectangleFactory.generateRectangle()
        rectangleArrayList.add(rectangle)
        return rectangle
    }
}
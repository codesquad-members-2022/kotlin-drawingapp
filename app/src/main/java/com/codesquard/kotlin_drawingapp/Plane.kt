package com.codesquard.kotlin_drawingapp

class Plane : RectangleListener {
    private val rectangleList = mutableListOf<Rectangle>()

    override fun onCreateRectangle(newRect: Rectangle) {
        rectangleList.add(newRect)
    }

    fun getNewRect(): Rectangle {
        RectangleModel(this).createNewRectangle()
        return rectangleList[rectangleList.size - 1]
    }

}

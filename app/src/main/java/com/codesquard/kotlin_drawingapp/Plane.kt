package com.codesquard.kotlin_drawingapp

class Plane : PlaneListener {

    override fun onCreateRectangle(): Rectangle {
        return RectangleFactory().getInstance()
    }

}

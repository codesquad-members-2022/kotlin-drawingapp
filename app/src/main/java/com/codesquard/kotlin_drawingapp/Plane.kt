package com.codesquard.kotlin_drawingapp

class Plane : PlaneListener {

    override fun onCreateRectangle(): RectangleViewModel {
        return RectangleViewModelFactory().getInstance()
    }

}

package com.codesquard.kotlin_drawingapp

import android.view.View

class Plane : PlaneListener {

    override fun onCreateRectangle(): RectangleViewModel {
        return RectangleViewModelFactory().getInstance()
    }

}

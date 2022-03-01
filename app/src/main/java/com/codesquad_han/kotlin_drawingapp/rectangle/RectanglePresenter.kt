package com.codesquad_han.kotlin_drawingapp.rectangle

import com.codesquad_han.kotlin_drawingapp.model.Plane

class RectanglePresenter(val plane: Plane, val rectangleView : RectangleContract.View) : RectangleContract.Presenter {

    init {
        rectangleView.presenter = this
    }

    override fun start() {

    }

}
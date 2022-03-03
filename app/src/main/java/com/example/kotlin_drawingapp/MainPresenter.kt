package com.example.kotlin_drawingapp

import com.example.kotlin_drawingapp.model.Rectangle

class MainPresenter(
    private val mainView: MainContract.View
) : MainContract.Presenter {

    private val plane = Plane()
    override fun drawRectangle() {
        plane.createRectangle()
        mainView.showRectangle(plane.getAllRectangle())
    }

    override fun selectRectangle(x: Float, y: Float) {

    }

    override fun setRectangleAlpha(index: Int, alpha: Int) {

    }

    override fun getRectangleInfo(index: Int) {

    }
}
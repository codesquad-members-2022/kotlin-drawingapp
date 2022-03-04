package com.example.kotlin_drawingapp

import com.example.kotlin_drawingapp.model.Rectangle

class MainPresenter(
    private val mainView: MainContract.View
) : MainContract.Presenter {

    private val plane = Plane()
    private var currentSelectedRectangle: Rectangle? = null
    override fun drawRectangle() {
        plane.createRectangle()
        mainView.showRectangle(plane.getAllRectangle())
    }

    override fun selectRectangle(x: Float, y: Float) {
        val rect = plane.getRectangleByPosition(x.toInt(), y.toInt())
        if (rect == null) {
            plane.clearRectangleBorder()
        } else {
            plane.createRectangleBorder(rect)
        }

        currentSelectedRectangle = rect
        val borderList = plane.getAllRectangleBorder()
        mainView.showRectangleBorder(borderList)
        rect?.let {
            mainView.showRectangleInfo(rect.rgb, rect.alpha)
        }
    }

    override fun setCurrentSelectedRectangleAlpha(alpha: Int) {
        val _currentSelectedRectangle = currentSelectedRectangle
        _currentSelectedRectangle?.let {
            val replacement = _currentSelectedRectangle.copy()
            replacement.alpha = alpha
            currentSelectedRectangle = replacement
            plane.modifyRectangle(_currentSelectedRectangle, replacement)
            mainView.showRectangle(plane.getAllRectangle())
        }
    }
}
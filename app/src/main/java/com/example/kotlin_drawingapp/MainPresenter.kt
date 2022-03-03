package com.example.kotlin_drawingapp

class MainPresenter(
    private val mainView: MainContract.View
) : MainContract.Presenter {

    private val plane = Plane()
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

        val borderList = plane.getAllRectangleBorder()
        mainView.showRectangleBorder(borderList)
        rect?.let {
            mainView.showRectangleInfo(rect.rgb, rect.alpha)
        }
    }

    override fun setRectangleAlpha(index: Int, alpha: Int) {

    }

    override fun getRectangleInfo(index: Int) {

    }
}
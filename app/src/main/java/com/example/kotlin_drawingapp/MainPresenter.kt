package com.example.kotlin_drawingapp

import com.example.kotlin_drawingapp.model.RectangleFactory
import com.example.kotlin_drawingapp.model.source.PlaneRepository

class MainPresenter(
    private val planeRepository: PlaneRepository,
    private val mainView: MainContract.View
) : MainContract.Presenter {

    override fun createRectangle() {
        planeRepository.saveRectangle(RectangleFactory.create())
        mainView.showRectangle(planeRepository.getAllRectangles())
    }

    override fun selectRectangle(x: Float, y: Float) {
        val rect = planeRepository.getRectangleByPosition(x.toInt(), y.toInt())
        if (rect == null) {
            planeRepository.clearRectangleBorder()
        } else {
            planeRepository.saveSelectedStatus(rect, true)
        }

        planeRepository.saveCurrentSelectedRectangle(rect)
        mainView.showRectangle(planeRepository.getAllRectangles())
        rect?.let {
            mainView.showRectangleInfo(rect.rgb, rect.alpha)
        }
    }

    override fun setCurrentSelectedRectangleAlpha(alpha: Int) {
        val tmpCurrentSelectedRectangle = planeRepository.getCurrentSelectedRectangle()
        tmpCurrentSelectedRectangle?.let {
            val replacement = tmpCurrentSelectedRectangle.copy()
            replacement.alpha = alpha
            planeRepository.saveCurrentSelectedRectangle(replacement)
            planeRepository.modifyRectangle(tmpCurrentSelectedRectangle, replacement)
            mainView.showRectangle(planeRepository.getAllRectangles())
        }
    }
}
package com.example.kotlin_drawingapp

import com.example.kotlin_drawingapp.model.RectangleFactory
import com.example.kotlin_drawingapp.model.source.DrawingDataSource

class MainPresenter(
    private val drawingRepository: DrawingDataSource,
    private val mainView: MainContract.View
) : MainContract.Presenter {

    override fun createRectangle() {
        drawingRepository.saveRectangle(RectangleFactory.create())
        mainView.showRectangle(drawingRepository.getAllRectangles())
    }

    override fun selectRectangle(x: Float, y: Float) {
        val rect = drawingRepository.getRectangleByPosition(x.toInt(), y.toInt())
        if (rect == null) {
            drawingRepository.clearRectangleBorder()
        } else {
            drawingRepository.saveSelectedStatus(rect, true)
        }

        drawingRepository.saveCurrentSelectedRectangle(rect)
        mainView.showRectangle(drawingRepository.getAllRectangles())
        rect?.let {
            mainView.showRectangleInfo(rect.rgb, rect.alpha)
        }
    }

    override fun setCurrentSelectedRectangleAlpha(alpha: Int) {
        val tmpCurrentSelectedRectangle = drawingRepository.getCurrentSelectedRectangle()
        tmpCurrentSelectedRectangle?.let {
            val replacement = tmpCurrentSelectedRectangle.copy()
            replacement.alpha = alpha
            drawingRepository.saveCurrentSelectedRectangle(replacement)
            drawingRepository.modifyRectangle(tmpCurrentSelectedRectangle, replacement)
            mainView.showRectangle(drawingRepository.getAllRectangles())
        }
    }
}
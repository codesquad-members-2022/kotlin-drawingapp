package com.example.kotlin_drawingapp

import com.example.kotlin_drawingapp.model.Color
import com.example.kotlin_drawingapp.model.RectangleFactory
import com.example.kotlin_drawingapp.model.draw.DrawObject
import com.example.kotlin_drawingapp.model.source.DrawingDataSource

class MainPresenter(
    private val drawingRepository: DrawingDataSource,
    private val mainView: MainContract.View
) : MainContract.Presenter {

    override fun createDrawObject(category: DrawObject.Category) {
        when (category) {
            DrawObject.Category.RECTANGLE -> drawingRepository.saveDrawObject(RectangleFactory.create())
            else -> { }
        }
        mainView.showDrawObject(drawingRepository.getAllDrawObject())
    }

    override fun selectDrawObject(x: Float, y: Float) {
        val drawObject = drawingRepository.getDrawObjectByPosition(x.toInt(), y.toInt())
        if (drawObject == null) {
            drawingRepository.clearDrawObjectBorder()
        } else {
            drawingRepository.saveSelectedStatus(drawObject, true)
        }

        drawingRepository.saveCurrentSelectedDrawObject(drawObject)
        mainView.showDrawObject(drawingRepository.getAllDrawObject())
        drawObject?.let {
            when (drawObject) {
                is DrawObject.Rectangle -> mainView.showDrawObjectInfo(drawObject.rgb, drawObject.alpha)
                else -> mainView.showDrawObjectInfo(Color(255, 255, 255), 10)
            }
        }
    }

    override fun setCurrentSelectedDrawObjectAlpha(alpha: Int) {
        val tmpCurrentSelectedDrawObject = drawingRepository.getCurrentSelectedDrawObject()
        tmpCurrentSelectedDrawObject?.let {
            val replacement = tmpCurrentSelectedDrawObject
            when (replacement) {
                is DrawObject.Rectangle -> replacement.alpha = alpha
            }
            drawingRepository.saveCurrentSelectedDrawObject(replacement)
            drawingRepository.modifyDrawObject(tmpCurrentSelectedDrawObject, replacement)
            mainView.showDrawObject(drawingRepository.getAllDrawObject())
        }
    }
}
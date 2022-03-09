package com.example.kotlin_drawingapp

import android.graphics.Bitmap
import android.graphics.Point
import android.util.Size
import com.example.kotlin_drawingapp.model.Color
import com.example.kotlin_drawingapp.model.ImageFactory
import com.example.kotlin_drawingapp.model.RectangleFactory
import com.example.kotlin_drawingapp.model.draw.DrawObject
import com.example.kotlin_drawingapp.model.source.DrawingDataSource

class MainPresenter(
    private val drawingRepository: DrawingDataSource,
    private val mainView: MainContract.View
) : MainContract.Presenter {

    override fun createRectangle() {
        drawingRepository.saveDrawObject(RectangleFactory.create())
        mainView.showDrawObject(drawingRepository.getAllDrawObject())
    }

    override fun createImage(bitmap: Bitmap) {
        drawingRepository.saveDrawObject(ImageFactory.create(bitmap))
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
        mainView.setCurrentSelectedDrawObject(drawObject)
        mainView.showDrawObject(drawingRepository.getAllDrawObject())
        drawObject?.let {
            when (drawObject) {
                is DrawObject.Rectangle -> mainView.showDrawObjectInfo(drawObject.rgb, drawObject.alpha)
                is DrawObject.Image -> mainView.showDrawObjectInfo(Color(255, 255, 255), drawObject.alpha)
            }
        }
    }

    override fun setCurrentSelectedDrawObjectAlpha(alpha: Int) {
        val tmpCurrentSelectedDrawObject = drawingRepository.getCurrentSelectedDrawObject()
        tmpCurrentSelectedDrawObject?.let {
            val replacement = tmpCurrentSelectedDrawObject
            when (replacement) {
                is DrawObject.Rectangle -> replacement.alpha = alpha
                is DrawObject.Image -> replacement.alpha = alpha
            }
            drawingRepository.saveCurrentSelectedDrawObject(replacement)
            drawingRepository.modifyDrawObject(tmpCurrentSelectedDrawObject, replacement)
            mainView.showDrawObject(drawingRepository.getAllDrawObject())
        }
    }
}
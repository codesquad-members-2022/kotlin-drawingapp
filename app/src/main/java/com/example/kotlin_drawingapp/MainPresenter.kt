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
        currentSelectedDrawObjectInfo(drawObject)
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

    override fun modifyDrawObjectPoint(target: DrawObject, point: Point) {
        val newDrawObject = when (target) {
            is DrawObject.Rectangle -> {
                DrawObject.Rectangle(
                    target.id,
                    target.currentSize,
                    point,
                    target.rgb,
                    target.alpha
                )
            }

            is DrawObject.Image -> {
                DrawObject.Image(
                    target.id,
                    target.currentSize,
                    point,
                    target.alpha,
                    target.bitmap
                )
            }
        }

        newDrawObject.selected = true
        drawingRepository.modifyDrawObject(target, newDrawObject)
        drawingRepository.saveCurrentSelectedDrawObject(newDrawObject)
        mainView.setCurrentSelectedDrawObject(newDrawObject)
        currentSelectedDrawObjectInfo(newDrawObject)
        mainView.showDrawObject(drawingRepository.getAllDrawObject())
    }

    override fun modifyDrawObjectSize(target: DrawObject, size: Size) {
        val newDrawObject = when (target) {
            is DrawObject.Rectangle -> {
                DrawObject.Rectangle(
                    target.id,
                    size,
                    target.currentPoint,
                    target.rgb,
                    target.alpha
                )
            }

            is DrawObject.Image -> {
                DrawObject.Image(
                    target.id,
                    size,
                    target.currentPoint,
                    target.alpha,
                    target.bitmap
                )
            }
        }

        newDrawObject.selected = true
        drawingRepository.modifyDrawObject(target, newDrawObject)
        drawingRepository.saveCurrentSelectedDrawObject(newDrawObject)
        mainView.setCurrentSelectedDrawObject(newDrawObject)
        currentSelectedDrawObjectInfo(newDrawObject)
        mainView.showDrawObject(drawingRepository.getAllDrawObject())
    }

    private fun currentSelectedDrawObjectInfo(drawObject: DrawObject?) {
        drawObject?.let {
            when (drawObject) {
                is DrawObject.Rectangle -> mainView.showDrawObjectInfo(drawObject.rgb, drawObject.alpha, drawObject.currentPoint, drawObject.currentSize)
                is DrawObject.Image -> mainView.showDrawObjectInfo(Color(255, 255, 255), drawObject.alpha, drawObject.currentPoint, drawObject.currentSize)
            }
        }
    }
}
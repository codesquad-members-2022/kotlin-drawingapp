package com.codesquard.kotlin_drawingapp.presenter

import android.graphics.Bitmap
import com.codesquard.kotlin_drawingapp.model.PhotoRectangle
import com.codesquard.kotlin_drawingapp.model.Plane
import com.codesquard.kotlin_drawingapp.model.Rectangle

class TaskPresenter(private val taskView: TaskContract.TaskView) : TaskContract.Presenter,
    RectangleListener {

    private val plane = Plane(this)
    private lateinit var selectedRect: Rectangle

    override fun setInitRectSizeAndMaxPoint(rectSize: Array<Int>, rectMaxPoint: Array<Int>) {
        plane.setInitRectSizeAndMaxPoint(rectSize, rectMaxPoint)
    }

    override fun addNewRectangle(photo: Bitmap?) {
        photo?.let {
            plane.createNewPhotoRectangle(photo)
        } ?: plane.createNewNormalRectangle()
    }

    override fun createNewTextRectangle() {
        plane.createNewTextRectangle()
    }

    override fun addNewTextRectangle(textRect: Rectangle, textSize: Array<Int>) {
        plane.addNewTextRectangle(textRect, textSize)
    }

    override fun selectRectangle(x: Float, y: Float) {
        plane.selectRectangle(x, y)
    }

    override fun selectRectangle(rectId: String) {
        plane.selectRectangle(rectId)
    }

    override fun changeAlphaValue(value: Float) {
        plane.updateAlpha(value)
    }

    override fun changeColor() {
        plane.updateColor()
    }

    override fun dragRectangle(x: Float, y: Float) {
        plane.dragRectangle(x, y)
    }

    override fun changePosition(x: Float, y: Float, isX: Boolean) {
        if (x > 220f && x < 255f) {
            if (y > 20f && y < 50) {
                plane.increasePosition(isX)
            } else if (y >= 50 && y < 75) {
                plane.reducePosition(isX)
            }
        }
    }

    override fun changeSize(x: Float, y: Float, isWidth: Boolean) {
        if (x > 220f && x < 255f) {
            if (y > 20f && y < 50) {
                plane.increaseSize(isWidth)
            } else if (y >= 50 && y < 75) {
                plane.reduceSize(isWidth)
            }
        }
    }

    override fun onCreateRectangle(newRect: Rectangle) {
        taskView.showRectangle(newRect)
    }

    override fun onMeasureTextSize(textRect: Rectangle) {
        taskView.measureTextSize(textRect)
    }

    override fun onSelectRectangle(selectedRect: Rectangle) {
        this.selectedRect = selectedRect
        getRectAlpha()
        getRectColor()
        getRectSize()
        getRectPosition()
        taskView.showSelectedRectangle(selectedRect)
        taskView.enableRectColorBtnAndSlider()
    }

    override fun onUnSelectRectangle() {
        taskView.showRectColor()
        taskView.showRectAlpha()
        taskView.showRectSize()
        taskView.showRectPosition()
        taskView.showSelectedRectangle()
    }

    override fun onUpdateRectangle() {
        getRectColor()
        getRectPosition()
        getRectSize()
        taskView.showSelectedRectangle()
    }

    override fun onDragRectangle(tempRect: Rectangle?) {
        getRectPosition()
        taskView.showDraggingRectangle(tempRect)
    }

    private fun getRectColor() {
        when (true) {
            selectedRect is PhotoRectangle -> {
                val colorText = "None"
                taskView.showRectColor(colorText)
                taskView.showEnabledColor(false)
            }
            else -> {
                val color = selectedRect.color
                val colorText =
                    "#${color[0].toString(16)}${color[1].toString(16)}${color[2].toString(16)}"
                taskView.showRectColor(colorText)
                taskView.showEnabledColor(true)
            }
        }
    }

    private fun getRectAlpha() {
        val alpha = selectedRect.alphaValue / 25
        taskView.showRectAlpha(alpha.toFloat())

    }

    private fun getRectSize() {
        val width = selectedRect.size[0].toString()
        val height = selectedRect.size[1].toString()
        taskView.showRectSize(width, height)
    }

    private fun getRectPosition() {
        val x = selectedRect.point[0].toInt().toString()
        val y = selectedRect.point[1].toInt().toString()
        taskView.showRectPosition(x, y)
    }

}



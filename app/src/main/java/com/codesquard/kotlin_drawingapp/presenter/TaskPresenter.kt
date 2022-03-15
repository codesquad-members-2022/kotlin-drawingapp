package com.codesquard.kotlin_drawingapp.presenter

import android.graphics.Bitmap
import com.codesquard.kotlin_drawingapp.model.PhotoRectangle
import com.codesquard.kotlin_drawingapp.model.Plane
import com.codesquard.kotlin_drawingapp.model.Rectangle

class TaskPresenter(private val taskView: TaskContract.TaskView) : TaskContract.Presenter,
    RectangleListener {

    private val plane = Plane(this)
    private var selectedRectIndex = -1

    override fun addNewRectangle(width: Float, height: Float, photo: Bitmap?) {
        photo?.let {
            plane.createNewPhotoRectangle(photo, width, height)
        } ?: plane.createNewNormalRectangle(width, height)
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
                plane.reducePosition(isX)
            } else if (y >= 50 && y < 75) {
                plane.increasePosition(isX)
            }
        }
    }

    override fun changeSize(x: Float, y: Float, isWidth: Boolean) {
        if (x > 220f && x < 255f) {
            if (y > 20f && y < 50) {
                plane.reduceSize(isWidth)
            } else if (y >= 50 && y < 75) {
                plane.increaseSize(isWidth)
            }
        }
    }

    override fun onCreateRectangle(newRect: Rectangle) {
        taskView.showRectangle(newRect)
    }

    override fun onMeasureTextSize(textRect: Rectangle) {
        taskView.measureTextSize(textRect)
    }

    override fun onSelectRectangle(index: Int) {
        selectedRectIndex = index
        getRectAlpha()
        getRectColor()
        getRectSize()
        getRectPosition()
        taskView.showSelectedRectangle()
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
            plane.getRectangle(selectedRectIndex) is PhotoRectangle -> {
                val colorText = "None"
                taskView.showRectColor(colorText)
                taskView.showEnabledColor(false)
            }
            else -> {
                val color = plane.getRectangle(selectedRectIndex).color
                val colorText =
                    "#${color[0].toString(16)}${color[1].toString(16)}${color[2].toString(16)}"
                taskView.showRectColor(colorText)
                taskView.showEnabledColor(true)
            }
        }
    }

    private fun getRectAlpha() {
        val alpha = plane.getRectangle(selectedRectIndex).alphaValue / 25
        taskView.showRectAlpha(alpha.toFloat())

    }

    private fun getRectSize() {
        val width = plane.getRectangle(selectedRectIndex).size[0].toString()
        val height = plane.getRectangle(selectedRectIndex).size[1].toString()
        taskView.showRectSize(width, height)
    }

    private fun getRectPosition() {
        val x = plane.getRectangle(selectedRectIndex).point[0].toInt().toString()
        val y = plane.getRectangle(selectedRectIndex).point[1].toInt().toString()
        taskView.showRectPosition(x, y)
    }

}



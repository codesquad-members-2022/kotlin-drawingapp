package com.codesquard.kotlin_drawingapp

import android.os.Build.VERSION_CODES.P
import android.util.Log

class TaskPresenter(val taskView: TaskContract.TaskView) : TaskContract.Presenter,
    RectangleListener {

    private val rectangleList = mutableListOf<Rectangle>()

    private val plane = Plane(this)

    override fun addNewRectangle() {
        val newRect = getNewRectangle()
        taskView.showRectangle(newRect)
    }

    override fun selectRectangle(x: Float, y: Float) {
        getSelectedRect(x, y)
        taskView.showSelectedRectangle()
    }

    override fun onCreateRectangle(newRect: Rectangle) {
        rectangleList.add(newRect)
    }

    override fun onSelectRectangle() {
        taskView.showSelectedRectangle()
    }

    private fun getNewRectangle(): Rectangle {
        plane.createNewRectangle()
        return rectangleList[rectangleList.size - 1]
    }

    private fun getSelectedRect(x: Float, y: Float) {
        plane.selectRectangle(x, y)
    }

}



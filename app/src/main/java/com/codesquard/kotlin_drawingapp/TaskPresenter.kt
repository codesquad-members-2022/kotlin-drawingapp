package com.codesquard.kotlin_drawingapp

import android.os.Build.VERSION_CODES.P
import android.util.Log

class TaskPresenter(val taskView: TaskContract.TaskView) : TaskContract.Presenter,
    RectangleListener {

    private val plane = Plane(this)

    override fun addNewRectangle() {
        plane.createNewRectangle()
    }

    override fun selectRectangle(x: Float, y: Float) {
        plane.selectRectangle(x, y)
    }

    override fun onCreateRectangle(newRect: Rectangle) {
        taskView.showRectangle(newRect)
    }

    override fun onSelectRectangle() {
        taskView.showSelectedRectangle()
    }

}



package com.codesquard.kotlin_drawingapp

class TaskPresenter(val taskView: TaskContract.TaskView) : TaskContract.Presenter {
    private val plane = Plane()

    override fun addNewRectangle() {
       val newRect = plane.getNewRect()
        taskView.showRectangle(newRect)
    }


}



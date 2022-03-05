package com.codesquard.kotlin_drawingapp

class TaskPresenter(val taskView: TaskContract.TaskView) : TaskContract.Presenter,
    RectangleListener {

    private val rectangleList = mutableListOf<Rectangle>()

    override fun addNewRectangle() {
        val newRect = getNewRect()
        taskView.showRectangle(newRect)
    }

    override fun onCreateRectangle(newRect: Rectangle) {
        rectangleList.add(newRect)
    }

    private fun getNewRect(): Rectangle {
        Plane(this).createNewRectangle()
        return rectangleList[rectangleList.size - 1]
    }

}



package com.codesquard.kotlin_drawingapp

class TaskPresenter(private val taskView: TaskContract.TaskView) : TaskContract.Presenter,
    RectangleListener {

    private val plane = Plane(this)

    override fun addNewRectangle() {
        plane.createNewRectangle()
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

    override fun onCreateRectangle(newRect: Rectangle) {
        taskView.showRectangle(newRect)
    }

    override fun onSelectRectangle(index: Int) {
        taskView.showSelectedRectangle(index)
        if (index > -1) {
            taskView.updateRect()
        }
    }

    override fun onUpdateRectangle() {
        taskView.showUpdatedRect()
    }

}



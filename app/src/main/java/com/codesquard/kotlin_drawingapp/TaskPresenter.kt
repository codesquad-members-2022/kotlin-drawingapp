package com.codesquard.kotlin_drawingapp

class TaskPresenter(private val taskView: TaskContract.TaskView) : TaskContract.Presenter,
    RectangleListener {

    private val plane = Plane(this)
    private var selectedRectIndex = -1

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
        selectedRectIndex = index
        if (index > -1) {
            taskView.updateRect()
        }
        getRectAlpha()
        getRectColor()
        taskView.showSelectedRectangle()
    }

    override fun onUpdateRectangle() {
        getRectColor()
        taskView.showSelectedRectangle()
    }

    private fun getRectColor() {
        if (selectedRectIndex == -1) {
            return taskView.showRectColor()
        }
        val color = plane.getRectangle(selectedRectIndex).getColor()
        val colorText = "#${color[0].toString(16)}${color[1].toString(16)}${color[2].toString(16)}"
        return taskView.showRectColor(colorText)
    }

    private fun getRectAlpha() {
        if (selectedRectIndex == -1) {
            return taskView.showRectAlpha()
        }
        val alpha = plane.getRectangle(selectedRectIndex).getAlpha() / 25
        return taskView.showRectAlpha(alpha.toFloat())
    }

}



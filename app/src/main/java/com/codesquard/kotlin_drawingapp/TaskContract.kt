package com.codesquard.kotlin_drawingapp

interface TaskContract {

    interface TaskView {
        fun showRectangle(newRect: Rectangle)

        fun showSelectedRectangleOrNoRectangle()

        fun showBackgroundColor(index: Int = -1)
    }

    interface Presenter {
        fun addNewRectangle()

        fun selectRectangle(x: Float, y: Float)

    }

}
package com.codesquard.kotlin_drawingapp

interface TaskContract {

    interface TaskView {
        fun showRectangle(newRect: Rectangle)

        fun showSelectedRectangleOrNoRectangle()

        fun showRectColor(index: Int = -1)

        fun showRectAlpha(index: Int = -1)
    }

    interface Presenter {
        fun addNewRectangle()

        fun selectRectangle(x: Float, y: Float)

    }

}
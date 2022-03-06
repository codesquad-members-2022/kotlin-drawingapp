package com.codesquard.kotlin_drawingapp

interface TaskContract {

    interface TaskView {
        fun showRectangle(newRect: Rectangle)

        fun showSelectedRectangleOrNoRectangle()
    }

    interface Presenter {
        fun addNewRectangle()

        fun selectRectangle(x: Float, y: Float)

    }

}
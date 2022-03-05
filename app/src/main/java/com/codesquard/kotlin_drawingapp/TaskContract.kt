package com.codesquard.kotlin_drawingapp

interface TaskContract {

    interface TaskView {
        fun showRectangle(newRect: Rectangle)
    }

    interface Presenter {
        fun addNewRectangle()
    }

}
package com.codesquard.kotlin_drawingapp

interface TaskContract {

    interface TaskView {
        fun showRectangle(newRect: Rectangle)

        fun showSelectedRectangle(index: Int = -1)

        fun showUpdatedRect()

        fun updateRect()

    }

    interface Presenter {
        fun addNewRectangle()

        fun selectRectangle(x: Float, y: Float)

        fun changeAlphaValue(value: Float)

        fun changeColor()

    }

}
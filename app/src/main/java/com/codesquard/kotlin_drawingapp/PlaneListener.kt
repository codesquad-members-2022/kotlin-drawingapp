package com.codesquard.kotlin_drawingapp

interface PlaneListener {

    interface LoadData {
        fun onTasksLoaded()
    }

    fun onCreateRectangle(): RectangleViewModel

}
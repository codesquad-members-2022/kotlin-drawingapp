package com.codesquard.kotlin_drawingapp

import android.view.View

interface PlaneListener {

    interface LoadData {
        fun onTasksLoaded()
    }

    fun onCreateRectangle(): RectangleViewModel

}
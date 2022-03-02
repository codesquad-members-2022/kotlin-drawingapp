package com.codesquard.kotlin_drawingapp

import android.view.View

interface TaskContract {

    interface TaskView {
        val viewList: ArrayList<View>

        fun showRectangle()
    }

    interface Presenter {
        fun onCreateView(): RectangleViewModel

        fun updateView(view: View, rectangle: RectangleViewModel)
    }

}
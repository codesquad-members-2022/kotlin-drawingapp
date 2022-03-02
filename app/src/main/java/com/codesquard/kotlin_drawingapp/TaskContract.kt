package com.codesquard.kotlin_drawingapp

import android.content.Context
import android.view.View

interface TaskContract {

    interface TaskView {
        fun showRectangle()
    }

    interface Presenter {
        fun onCreateView(context: Context): TaskPresenter.CustomView
    }

}
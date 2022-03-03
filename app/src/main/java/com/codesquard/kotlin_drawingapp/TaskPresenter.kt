package com.codesquard.kotlin_drawingapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class TaskPresenter(val taskView: TaskContract.TaskView) : TaskContract.Presenter {
    private val plane: PlaneListener = Plane()
    private val rectangleList = ArrayList<RectangleViewModel>()

    /*override fun onCreateView(context: Context): CustomView {
        return CustomView(context)
    }*/



    }



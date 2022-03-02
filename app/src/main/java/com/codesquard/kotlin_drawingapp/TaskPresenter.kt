package com.codesquard.kotlin_drawingapp

import android.graphics.Color
import android.view.View

class TaskPresenter(val taskView: TaskContract.TaskView) : TaskContract.Presenter {
    private val plane: PlaneListener = Plane()
    val viewList = taskView.viewList

    override fun onCreateView(): RectangleViewModel {
        return plane.onCreateRectangle()
    }

    override fun updateView(view: View, rectangle: RectangleViewModel) {
        view.setBackgroundColor(
            Color.rgb(
                rectangle.getColor()[0],
                rectangle.getColor()[1],
                rectangle.getColor()[2]
            )
        )
        view.alpha = rectangle.getAlpha()
        view.x = rectangle.getPoint()[0].toFloat()
        view.y = rectangle.getPoint()[1].toFloat()
        view.layoutParams.width = rectangle.getSize()[0]
        view.layoutParams.height = rectangle.getSize()[1]
    }

}
package com.codesquard.kotlin_drawingapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class TaskPresenter(val taskView: TaskContract.TaskView) : TaskContract.Presenter {
    private val plane: PlaneListener = Plane()
    private val rectangleList = ArrayList<RectangleViewModel>()

    override fun onCreateView(context: Context): CustomView {
        return CustomView(context)
    }

    inner class CustomView(context: Context) : View(context) {
        private val rectangle = plane.onCreateRectangle()

        init {
            rectangleList.add(rectangle)
        }

        override fun onDraw(canvas: Canvas?) {
            super.onDraw(canvas)

            val paint = Paint()
            val alpha = rectangle.getAlpha()
            val r = rectangle.getColor()[0]
            val g = rectangle.getColor()[1]
            val b = rectangle.getColor()[2]
            paint.color = Color.argb(alpha, r, g, b)

            val x = rectangle.getPoint()[0]
            val y = rectangle.getPoint()[1]
            canvas?.drawRect(x, y, 150f + x, 120f + y, paint)
        }

    }


}

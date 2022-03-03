package com.example.kotlin_drawingapp

import com.example.kotlin_drawingapp.model.Color
import com.example.kotlin_drawingapp.model.Rectangle

class MainContract {
    interface View {
        fun showRectangle(rectangles: List<Rectangle>)

        fun showRectangleInfo(color: Color, alpha: Int)

        fun showRectangleBorder(border: Rectangle?)

        fun setRectangleAlpha(index: Int, alpha: Int)
    }

    interface Presenter {
        fun drawRectangle()

        fun selectRectangle(x: Float, y: Float)

        fun setRectangleAlpha(index: Int, alpha: Int)

        fun getRectangleInfo(index: Int)
    }
}
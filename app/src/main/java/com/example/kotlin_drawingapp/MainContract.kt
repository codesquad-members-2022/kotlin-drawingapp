package com.example.kotlin_drawingapp

import com.example.kotlin_drawingapp.model.Color
import com.example.kotlin_drawingapp.model.Rectangle
import com.example.kotlin_drawingapp.model.RectangleBorder

class MainContract {
    interface View {
        fun showRectangle(rectangles: List<Rectangle>)

        fun showRectangleInfo(color: Color, alpha: Int)

        fun showRectangleBorder(rectangleBorderList: List<RectangleBorder>)
    }

    interface Presenter {
        fun drawRectangle()

        fun selectRectangle(x: Float, y: Float)

        fun setCurrentSelectedRectangleAlpha(alpha: Int)
    }
}
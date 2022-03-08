package com.example.kotlin_drawingapp

import com.example.kotlin_drawingapp.model.Color
import com.example.kotlin_drawingapp.model.draw.DrawObject

class MainContract {
    interface View {
        fun showDrawObject(drawObject: List<DrawObject>)

        fun showDrawObjectInfo(color: Color, alpha: Int)
    }

    interface Presenter {
        fun createDrawObject(category: DrawObject.Category)

        fun selectDrawObject(x: Float, y: Float)

        fun setCurrentSelectedDrawObjectAlpha(alpha: Int)
    }
}
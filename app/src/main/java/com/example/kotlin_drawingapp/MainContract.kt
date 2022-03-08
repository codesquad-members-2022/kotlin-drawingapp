package com.example.kotlin_drawingapp

import android.graphics.Bitmap
import com.example.kotlin_drawingapp.model.Color
import com.example.kotlin_drawingapp.model.draw.DrawObject

class MainContract {
    interface View {
        fun showDrawObject(drawObject: List<DrawObject>)

        fun showDrawObjectInfo(color: Color, alpha: Int)
    }

    interface Presenter {
        fun createRectangle()

        fun createImage(bitmap: Bitmap)

        fun selectDrawObject(x: Float, y: Float)

        fun setCurrentSelectedDrawObjectAlpha(alpha: Int)
    }
}
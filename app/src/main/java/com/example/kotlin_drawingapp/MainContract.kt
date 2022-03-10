package com.example.kotlin_drawingapp

import android.graphics.Bitmap
import android.graphics.Point
import android.util.Size
import com.example.kotlin_drawingapp.model.Color
import com.example.kotlin_drawingapp.model.draw.DrawObject

class MainContract {
    interface View {
        fun showDrawObject(drawObject: List<DrawObject>)

        fun showDrawObjectInfo(color: Color, alpha: Int, point: Point, size: Size)

        fun setCurrentSelectedDrawObject(drawObject: DrawObject?)
    }

    interface Presenter {
        fun createRectangle()

        fun createImage(bitmap: Bitmap)

        fun selectDrawObject(x: Float, y: Float)

        fun setCurrentSelectedDrawObjectAlpha(alpha: Int)

        fun modifyDrawObjectProperty(target: DrawObject, point: Point, size: Size)
    }
}
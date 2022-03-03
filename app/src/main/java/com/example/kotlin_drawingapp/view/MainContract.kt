package com.example.kotlin_drawingapp.view

import android.widget.ImageView
import com.example.kotlin_drawingapp.shapemodel.Rectangle

interface MainContract {
    interface View {
        fun select(border: ImageView?, rectImage: ImageView?, rect: Rectangle?)
    }

    interface Presenter {
        fun create(): ImageView
        fun select(x: Float, y: Float)
    }
}
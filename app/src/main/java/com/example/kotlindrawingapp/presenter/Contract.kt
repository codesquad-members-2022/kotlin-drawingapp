package com.example.kotlindrawingapp.presenter

import android.graphics.Bitmap
import com.example.kotlindrawingapp.domain.figure.Figure
import com.example.kotlindrawingapp.view.Sizeable

interface Contract {

    interface View {
        fun showLayer(index: Int)

    }

    interface Presenter {
        fun loadRandomText(callback: Sizeable)

        fun loadFigure()

        fun loadFigure(figure: Figure)

        fun loadPicture(bitmap: Bitmap)

        fun loadText(size: Pair<Int, Int>, text: String)

        fun removeFigure(figure: Figure)

        fun editRectangleAlpha(alpha: Int)

        fun editFigure(x: Float, y: Float)

        fun editFigurePointX(x: Float)

        fun editFigurePointY(y: Float)

        fun editFigurePoint(x: Float, y: Float)

        fun editFigureWidth(width: Int)

        fun editFigureHeight(height: Int)

        fun loadSelectedLayer(figure: Figure?)

        fun sendToBack(index: Int)

        fun sendToFront(index: Int)

        fun swap(from: Int, to: Int)
    }
}
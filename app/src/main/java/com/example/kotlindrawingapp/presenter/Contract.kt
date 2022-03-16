package com.example.kotlindrawingapp.presenter

import android.graphics.Bitmap
import com.example.kotlindrawingapp.domain.figure.Figure
import com.example.kotlindrawingapp.view.LayerCustomView
import com.example.kotlindrawingapp.view.Sizeable

interface Contract {

    interface View {
        fun showLayer(layerCustomView: LayerCustomView)

        fun showSelectedLayer(layerCustomView: LayerCustomView)

        fun showNotSelectedLayer(layerCustomView: LayerCustomView)
    }

    interface Presenter {
        fun loadRandomText(callback: Sizeable)

        fun loadFigure(layerCustomView: LayerCustomView)

        fun loadFigure(figure: Figure)

        fun loadPicture(bitmap: Bitmap, layerCustomView: LayerCustomView)

        fun loadText(size: Pair<Int, Int>, text: String, layerCustomView: LayerCustomView)

        fun removeFigure(figure: Figure)

        fun editRectangleAlpha(alpha: Int)

        fun editFigurePointX(x: Float)

        fun editFigurePointY(y: Float)

        fun editFigurePoint(x: Float, y: Float)

        fun editFigureWidth(width: Int)

        fun editFigureHeight(height: Int)

        fun editLayer(figure: Figure?)

    }
}
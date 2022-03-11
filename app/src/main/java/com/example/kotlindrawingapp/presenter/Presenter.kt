package com.example.kotlindrawingapp.presenter

import android.graphics.Bitmap
import com.example.kotlindrawingapp.domain.figure.Figure
import com.example.kotlindrawingapp.repository.FigureRepository

class Presenter(
    private val view: Contract.View,
    private val repository: FigureRepository,
) : Contract.Presenter {

    val selectedSquare = repository.selectedSquare
    val plane = repository.plane

    override fun loadFigure() {
        repository.addSquare()
    }

    override fun loadFigure(figure: Figure) {
        repository.addSquare(figure)
    }

    override fun loadPicture(bitmap: Bitmap) {
        repository.addPicture(bitmap)
    }

    override fun loadText() {
        repository.addText()
    }

    override fun removeFigure(figure: Figure) {
        repository.deleteSquare(figure)
    }

    override fun editRectangleAlpha(alpha: Int) {
        repository.updateSquare(alpha)
    }

    override fun editFigurePointX(x: Float) {
        repository.updatePointX(x)
    }

    override fun editFigurePointY(y: Float) {
        repository.updatePointY(y)
    }

    override fun editFigurePoint(x: Float, y: Float) {
        repository.updatePoint(x, y)
    }

    override fun editFigureWidth(width: Int) {
        repository.updateWidth(width)
    }

    override fun editFigureHeight(height: Int) {
        repository.updateHeight(height)
    }
}
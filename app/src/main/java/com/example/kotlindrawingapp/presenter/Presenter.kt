package com.example.kotlindrawingapp.presenter

import android.graphics.Bitmap
import com.example.kotlindrawingapp.domain.figure.Figure
import com.example.kotlindrawingapp.domain.figure.text.Text.Companion.generateText
import com.example.kotlindrawingapp.repository.FigureRepository
import com.example.kotlindrawingapp.view.Sizeable

class Presenter(
    private val view: Contract.View,
    private val repository: FigureRepository,
) : Contract.Presenter {

    val selectedSquare = repository.selectedSquare
    val plane = repository.plane

    override fun loadRandomText(callback: Sizeable) {
        callback.getWidthAndHeight(generateText())
    }

    override fun loadFigure() {
        repository.addSquare()
    }

    override fun loadFigure(figure: Figure) {
        repository.addSquare(figure)
    }

    override fun loadPicture(bitmap: Bitmap) {
        repository.addPicture(bitmap)
    }

    override fun loadText(size: Pair<Int, Int>, text: String) {
        repository.addText(size, text)
    }

    override fun removeFigure(figure: Figure) {
        repository.deleteSquare(figure)
    }

    override fun editRectangleAlpha(alpha: Int) {
        repository.updateSquare(alpha)
    }

    override fun editFigure(x: Float, y: Float) {
        repository.updateFigure(x, y)
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

    override fun loadSelectedLayer(figure: Figure?) {
        val index = plane.value?.findByFigure(selectedSquare.value)
        index ?: return
        view.showLayer(index)
    }

    override fun sendToBack(index: Int) {
        repository.updateLastPositionFromIndex(index)
    }

    override fun sendToFront(index: Int) {
        repository.updateFirstPositionFromIndex(index)
    }

    override fun swap(from: Int, to: Int) {
        repository.swap(from, to)
    }

}
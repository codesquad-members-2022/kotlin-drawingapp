package com.example.kotlindrawingapp.presenter

import android.graphics.Bitmap
import com.example.kotlindrawingapp.domain.figure.Figure
import com.example.kotlindrawingapp.domain.figure.text.Text.Companion.generateText
import com.example.kotlindrawingapp.repository.FigureRepository
import com.example.kotlindrawingapp.view.LayerCustomView
import com.example.kotlindrawingapp.view.Sizeable

class Presenter(
    private val view: Contract.View,
    private val repository: FigureRepository,
) : Contract.Presenter {

    val selectedSquare = repository.selectedSquare
    val plane = repository.plane
    private val layerList = mutableListOf<LayerCustomView>()

    override fun loadRandomText(callback: Sizeable) {
        callback.getWidthAndHeight(generateText())
    }

    override fun loadFigure(layerCustomView: LayerCustomView) {
        repository.addSquare()
        layerList.add(layerCustomView)
        view.showLayer(layerCustomView)
    }

    override fun loadFigure(figure: Figure) {
        repository.addSquare(figure)
    }

    override fun loadPicture(bitmap: Bitmap, layerCustomView: LayerCustomView) {
        repository.addPicture(bitmap)
        layerList.add(layerCustomView)
        view.showLayer(layerCustomView)
    }

    override fun loadText(size: Pair<Int, Int>, text: String, layerCustomView: LayerCustomView) {
        repository.addText(size, text)
        layerList.add(layerCustomView)
        view.showLayer(layerCustomView)
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

    override fun editLayer(figure: Figure?) {
        val index = plane.value?.findByFigure(selectedSquare.value)
        index ?: return
        layerList.forEachIndexed { idx, layerCustomView ->
            if (idx == index) {
                view.showSelectedLayer(layerCustomView)
            } else {
                view.showNotSelectedLayer(layerCustomView)
            }
        }
    }
}
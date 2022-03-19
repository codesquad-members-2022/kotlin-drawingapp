package com.example.kotlindrawingapp.repository

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import com.example.kotlindrawingapp.domain.figure.plane.Plane
import com.example.kotlindrawingapp.domain.figure.*

class FigureRepository {

    private val _plane: Plane = Plane.of(listOf())
    val plane = MutableLiveData<Plane>(_plane)
    val selectedSquare = _plane.selectedSquare

    fun addSquare() {
        _plane.addFigure(FigureFactory.createSquare(Size()))
        plane.value = _plane
    }

    fun addSquare(figure: Figure) {
        _plane.addFigure(figure)
        plane.value = _plane
    }

    fun addPicture(bitmap: Bitmap) {
        _plane.addFigure(FigureFactory.createPicture(Size(), bitmap))
        plane.value = _plane
    }


    fun addText(size: Pair<Int, Int>, text: String) {
        _plane.addFigure(FigureFactory.createText(text, Size(size.first, size.second)))
        plane.value = _plane
    }

    fun deleteSquare(figure: Figure) {
        _plane.removeFigure(figure)
        plane.value = _plane
    }

    fun updateSquare(alpha: Int) {
        _plane.updateAlpha(Alpha(alpha))
        plane.value = _plane
    }

    fun updatePointX(x: Float) {
        _plane.updatePointX(x)
        plane.value = _plane
    }

    fun updatePointY(y: Float) {
        _plane.updatePointY(y)
        plane.value = _plane
    }

    fun updatePoint(x: Float, y: Float) {
        _plane.updatePointX(x)
        _plane.updatePointY(y)
        plane.value = _plane
    }

    fun updateWidth(width: Int) {
        _plane.updateWidth(width)
        plane.value = _plane
    }

    fun updateHeight(height: Int) {
        _plane.updateHeight(height)
        plane.value = _plane
    }

    fun updateFigure(x: Float, y: Float) {
        updatePoint(x, y)
    }

    fun swap(from: Int, to: Int) {
        _plane.swap(from, to)
        plane.value = _plane
    }

    fun updateLastPositionFromIndex(index: Int) {
        _plane.updateLastPosition(index)
        plane.value = _plane
    }

    fun updateFirstPositionFromIndex(index: Int) {
        _plane.updateFirstPosition(index)
        plane.value = _plane
    }
}
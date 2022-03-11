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

    fun addPicture(bitmap: Bitmap) {
        _plane.addFigure(FigureFactory.createPicture(Size(), bitmap))
        plane.value = _plane
    }

    fun updateSquare(alpha: Int) {
        _plane.updateAlpha(Alpha(alpha))
        plane.value = _plane
    }
}
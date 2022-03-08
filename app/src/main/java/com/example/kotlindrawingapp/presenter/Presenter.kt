package com.example.kotlindrawingapp.presenter

import android.graphics.Bitmap
import com.example.kotlindrawingapp.repository.FigureRepository

class Presenter(
    private val view: Contract.View,
    private val repository: FigureRepository,
) : Contract.Presenter {

    val selectedSquare = repository.selectedSquare
    val plane = repository.plane

    override fun loadRectangle() {
        repository.addSquare()
    }

    override fun loadPicture(bitmap: Bitmap) {
        repository.addPicture(bitmap)
    }

    override fun editRectangleAlpha(alpha: Int) {
        repository.updateSquare(alpha)
    }
}
package com.example.kotlindrawingapp.presenter

import com.example.kotlindrawingapp.repository.SquareRepository

class Presenter(
    private val view: Contract.View,
    private val repository: SquareRepository,
) : Contract.Presenter {

    val selectedSquare = repository.selectedSquare
    val plane = repository.plane

    override fun loadRectangle() {
        repository.addSquare()
    }

    override fun editRectangleAlpha(alpha: Int) {
        repository.updateSquare(alpha)
    }
}
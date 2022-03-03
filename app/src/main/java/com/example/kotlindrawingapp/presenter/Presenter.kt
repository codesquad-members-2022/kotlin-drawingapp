package com.example.kotlindrawingapp.presenter

import com.example.kotlindrawingapp.CustomCanvas
import com.example.kotlindrawingapp.repository.SquareRepository

class Presenter(
    private val view: Contract.View,
    val repository: SquareRepository,
    private val customView: CustomCanvas
) : Contract.Presenter {

    init {
        customView.setPresent(this)
    }

    override fun drawRectangle() {
        val square = repository.addSquare()
        customView.drawRectangle(square)
    }

    override fun loadBoard(index: Int) {
        if (index != NOT_FOUND) {
            val square = repository.findSquare(index)
            repository.selectedSquare = square
            val rgb = square.rgb.decimalToHex()
            view.updateBoard(rgb, square.alpha.alpha)
        }
    }

    override fun editRectangleAlpha(alpha: Int) {
        repository.selectedSquare
            ?.let {
                it.alpha.alpha = alpha
                customView.drawRectangle(it)
            }
    }

    companion object {
        private const val NOT_FOUND = -1
    }
}
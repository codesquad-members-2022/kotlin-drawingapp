package com.example.kotlindrawingapp.square

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class Plane private constructor(private var _squares: List<Square>) {

    private var item: Square? = null // TODO LiveData
    private val _selectedSquare = MutableLiveData<Square?>()
    val selectedSquare: LiveData<Square?>
        get() = _selectedSquare

    val squares: List<Square>
        get() = _squares

    fun addSquare(square: Square) {
        _squares = _squares + square
    }

    fun count(): Int = _squares.size

    fun selected(point: Point): Square? {
        val filter = _squares.filter { square -> square.isSelected(point) }
        return if(filter.isEmpty()) null else filter.last()
    }

    fun touchPoint(point: Point) {
        item?.let {
            item = null
            _selectedSquare.value = item
        }

        selected(point)?.let {
            item = it
            _selectedSquare.value = item
        }
    }

    fun updateRGB() {
        item?.update(RGB(100, 100, 100))
        _selectedSquare.value = item
    }

    fun updateAlpha(alpha: Alpha) {
        item?.update(alpha)
        _squares.find { it == item }?.alpha = alpha
        _selectedSquare.value = item
    }

    fun findByIndex(idx: Int): Square {
        return _squares[idx]
    }

    companion object {
        fun of(squares: List<Square>): Plane {
            return Plane(squares)
        }
    }
}
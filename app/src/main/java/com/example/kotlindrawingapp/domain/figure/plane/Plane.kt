package com.example.kotlindrawingapp.domain.figure.plane

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlindrawingapp.domain.figure.Alpha
import com.example.kotlindrawingapp.domain.figure.Figure
import com.example.kotlindrawingapp.domain.figure.Point
import com.example.kotlindrawingapp.domain.figure.RGB
import com.example.kotlindrawingapp.domain.figure.square.Square

class Plane private constructor(private var _squares: List<Figure>) {

    private var item: Figure? = null
    private val _selectedSquare = MutableLiveData<Figure?>()
    val selectedSquare: LiveData<Figure?>
        get() = _selectedSquare

    val squares: List<Figure>
        get() = _squares

    fun addFigure(figure: Figure) {
        _squares = _squares + figure
    }

    fun count(): Int = _squares.size

    fun selected(point: Point): Figure? {
        val filter = _squares.filter { figure -> figure.isSelected(point) }
        return if (filter.isEmpty()) null else filter.last()
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

    fun findByIndex(idx: Int): Figure {
        return _squares[idx]
    }

    companion object {
        fun of(squares: List<Square>): Plane {
            return Plane(squares)
        }
    }
}
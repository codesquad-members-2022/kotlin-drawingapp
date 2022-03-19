package com.example.kotlindrawingapp.domain.figure.plane

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlindrawingapp.domain.figure.*
import com.example.kotlindrawingapp.domain.figure.square.Square
import com.example.kotlindrawingapp.domain.figure.text.Text

class Plane private constructor(private val _squares: MutableList<Figure>) {

    private var item: Figure? = null
    private val _selectedSquare = MutableLiveData<Figure?>()
    val selectedSquare: LiveData<Figure?>
        get() = _selectedSquare

    val squares: List<Figure>
        get() = _squares

    fun addFigure(figure: Figure?) {
        figure?.let {
            _squares.add(it)
        }
    }

    fun removeFigure(figure: Figure?) {
        _squares.remove(figure)
    }

    fun count(): Int = _squares.size

    fun selected(point: Point): Figure? {
        val filter = _squares.filter { figure ->
            when (figure) {
                is Text -> figure.isSelected(point)
                else -> figure.isSelected(point)
            }
        }
        return if (filter.isEmpty()) null else filter.last()
    }

    fun touchPoint(point: Point) {
        selected(point)?.let {
            item = it
            _selectedSquare.value = item
        } ?: run {
            item = null
            _selectedSquare.value = item
        }
    }

    fun updateRGB() {
        item?.rgb = RGB(100, 100, 100)
        _selectedSquare.value = item
    }

    fun updateAlpha(alpha: Alpha) {
        item?.let {
            it.alpha = alpha
            val index = _squares.indexOf(it)
            _squares[index].alpha = alpha
            _selectedSquare.value = it
        }
    }

    fun findByIndex(idx: Int): Figure {
        return _squares[idx]
    }

    fun findByFigure(figure: Figure?): Int {
        return _squares.indexOf(figure)
    }

    fun updatePointX(newX: Float) {
        item?.let {
            val y = it.point.y
            it.point = Point(newX, y)
            val index = _squares.indexOf(it)
            _squares[index].point = Point(newX, y)
            _selectedSquare.value = it
        }
    }

    fun updatePointY(newY: Float) {
        item?.let {
            val x = it.point.x
            it.point = Point(x, newY)
            val index = _squares.indexOf(it)
            _squares[index].point = Point(x, newY)
            _selectedSquare.value = it
        }
    }

    fun updateWidth(newWidth: Int) {
        item?.let {
            val height = it.size.height
            it.size = Size(newWidth, height)
            val index = _squares.indexOf(it)
            _squares[index].size = Size(newWidth, height)
            _selectedSquare.value = it
        }
    }

    fun updateHeight(newHeight: Int) {
        item?.let {
            val width = it.size.width
            it.size = Size(width, newHeight)
            val index = _squares.indexOf(it)
            _squares[index].size = Size(width, newHeight)
            _selectedSquare.value = it
        }
    }

    fun swap(from: Int, to: Int) {
        _squares[to] = _squares[from].also { _squares[from] = _squares[to] }
    }

    fun updateLastPosition(index: Int) {
        _squares.add(_squares[index])
        _squares.removeAt(index)
    }

    fun updateFirstPosition(index: Int) {
        _squares.add(0, _squares[index])
        _squares.removeAt(index + 1)
    }

    companion object {
        fun of(squares: List<Square>): Plane {
            return Plane(squares.toMutableList())
        }
    }
}
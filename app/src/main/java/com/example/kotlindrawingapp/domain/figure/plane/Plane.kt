package com.example.kotlindrawingapp.domain.figure.plane

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlindrawingapp.domain.figure.*
import com.example.kotlindrawingapp.domain.figure.square.Square

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
        } ?: run {
            item = null
            _selectedSquare.value = item
        }
    }

    fun updateRGB() {
        item?.update(RGB(100, 100, 100))
        _selectedSquare.value = item
    }

    fun updateAlpha(alpha: Alpha) {
        item?.let {
            it.update(alpha)
            val index = _squares.indexOf(it)
            _squares[index].update(alpha)
            _selectedSquare.value = it
        }
    }

    fun findByIndex(idx: Int): Figure {
        return _squares[idx]
    }

    fun updatePointX(newX: Float) {
        item?.let {
            val y = it.point.y
            it.update(Point(newX, y))
            val index = _squares.indexOf(it)
            _squares[index].update(Point(newX, y))
            _selectedSquare.value = it
        }
    }

    fun updatePointY(newY: Float) {
        item?.let {
            val x = it.point.x
            it.update(Point(x, newY))
            val index = _squares.indexOf(it)
            _squares[index].update(Point(x, newY))
            _selectedSquare.value = it
        }
    }

    fun updateWidth(newWidth: Int) {
        item?.let {
            val height = it.size.height
            it.update(Size(newWidth, height))
            val index = _squares.indexOf(it)
            _squares[index].update(Size(newWidth, height))
            _selectedSquare.value = it
        }
    }

    fun updateHeight(newHeight: Int) {
        item?.let {
            val width = it.size.width
            it.update(Size(width, newHeight))
            val index = _squares.indexOf(it)
            _squares[index].update(Size(width, newHeight))
            _selectedSquare.value = it
        }
    }

    companion object {
        fun of(squares: List<Square>): Plane {
            return Plane(squares.toMutableList())
        }
    }
}
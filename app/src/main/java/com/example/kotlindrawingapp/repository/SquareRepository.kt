package com.example.kotlindrawingapp.repository

import androidx.lifecycle.MutableLiveData
import com.example.kotlindrawingapp.square.Plane
import com.example.kotlindrawingapp.square.*

class SquareRepository {

    private val _plane: Plane = Plane.of(listOf())
    val plane = MutableLiveData<Plane>(_plane)
    val selectedSquare = _plane.selectedSquare
    private var index = 0
    private val squares = listOf<Square>(
        SquareFactory.createSquare(Point(10F, 200F), Size(), RGB(245, 0, 245), Alpha(9)),
        SquareFactory.createSquare(Point(110F, 180F), Size(), RGB(43, 124, 95), Alpha(5)),
        SquareFactory.createSquare(Point(590F, 90F), Size(), RGB(98, 244, 15), Alpha(7)),
        SquareFactory.createSquare(Point(330F, 450F), Size(), RGB(125, 39, 99), Alpha(1))
    )

    fun addSquare() {
        _plane.addSquare(squares[index++])
        plane.value = _plane
    }

    fun updateSquare(alpha: Int) {
        _plane.updateAlpha(Alpha(alpha))
        plane.value = _plane
    }
}
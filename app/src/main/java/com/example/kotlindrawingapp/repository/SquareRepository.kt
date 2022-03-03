package com.example.kotlindrawingapp.repository

import com.example.kotlindrawingapp.plane.Plane
import com.example.kotlindrawingapp.square.*

class SquareRepository {

    val plane: Plane = Plane.of(listOf())
    private var index = 0
    private val squares = listOf<Square>(
        SquareFactory.createSquare(Point(10, 200), Size(), RGB(245, 0, 245), Alpha(9)),
        SquareFactory.createSquare(Point(110, 180), Size(), RGB(43, 124, 95), Alpha(5)),
        SquareFactory.createSquare(Point(590, 90), Size(), RGB(98, 244, 15), Alpha(7)),
        SquareFactory.createSquare(Point(330, 450), Size(), RGB(125, 39, 99), Alpha(1))
    )

    fun addSquare(): Square {
        plane.addSquare(squares[index])
        return squares[index++]
    }

    fun findSquare(index: Int): Square {
        return plane.findSquare(index)
    }
}
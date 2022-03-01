package com.example.kotlindrawingapp.plane

import com.example.kotlindrawingapp.square.Square

class Plane private constructor(private var _squares: List<Square>) {
    val squares: List<Square>
        get() = _squares

    fun addSquare(square: Square) {
        _squares = _squares + square
    }

    fun count(): Int = _squares.size

    fun findSquare(index: Int): Square = _squares[index]

    fun contain(square: Square): Boolean {
        return _squares.any { it.point == square.point }
    }

    companion object {
        fun of(squares: List<Square>): Plane {
            return Plane(squares)
        }
    }
}
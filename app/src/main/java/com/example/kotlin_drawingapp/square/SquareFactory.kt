package com.example.kotlin_drawingapp.square

object SquareFactory {
    private val id = ID()
    private val point = Point()
    private val size = Size()
    private val rgb = RGB()
    private val alpha = Alpha()
    fun createSquare(): Square {
        return Square(id, point, size, rgb, alpha)
    }
}
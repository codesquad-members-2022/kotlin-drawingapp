package com.example.kotlindrawingapp.domain.figure

data class Point(val x: Float, val y: Float) {

    companion object {
        fun generatePoint(): Point {
            val pointX = (0..1500).random().toFloat()
            val pointY = (0..1500).random().toFloat()
            return Point(pointX, pointY)
        }
    }
}

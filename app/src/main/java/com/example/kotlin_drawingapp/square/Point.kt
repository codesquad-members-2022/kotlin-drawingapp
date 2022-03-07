package com.example.kotlin_drawingapp.square

class Point {
    fun createPoint(): Array<String> {
        val pointArray = arrayOf("0", "0")
        pointArray[0] = (1..600).random().toString()
        pointArray[1] = (1..600).random().toString()

        return pointArray
    }
}
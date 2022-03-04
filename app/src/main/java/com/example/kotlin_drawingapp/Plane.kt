package com.example.kotlin_drawingapp

class Plane {
    fun randomLocation(): Array<Int> {
        val location = arrayOf(0, 0)
        location[0] = (0..1500).random()
        location[1] = (0..1000).random()

        return location
    }
}
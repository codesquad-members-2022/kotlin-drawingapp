package com.example.kotlin_drawingapp

import android.graphics.Rect

class Plane {
    private val rectList = mutableListOf<Rect>()

    fun randomLocation(): Array<Int> {
        val location = arrayOf(0, 0)
        location[0] = (0..1500).random()
        location[1] = (0..1000).random()

        return location
    }

    fun addRect(rect: Rect): MutableList<Rect>{
        rectList.add(rect)
        return rectList
    }

    fun countRect(rectList: MutableList<Rect>): Int {
        return rectList.size
    }
}
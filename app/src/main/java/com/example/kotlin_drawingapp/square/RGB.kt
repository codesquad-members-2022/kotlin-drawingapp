package com.example.kotlin_drawingapp.square

class RGB {
    fun createRGB(): Array<String> {
        val rgbArray = arrayOf("0", "0", "0")
        rgbArray[0] = (0..255).random().toString()
        rgbArray[1] = (0..255).random().toString()
        rgbArray[2] = (0..255).random().toString()

        return rgbArray
    }
}
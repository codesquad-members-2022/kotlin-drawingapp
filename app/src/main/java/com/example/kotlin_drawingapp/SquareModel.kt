package com.example.kotlin_drawingapp

import kotlin.random.Random

class SquareModel : SquareModelFrame{
    companion object {
        const val STRING_LENGTH = 3
    }

    private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    override fun createSeparatedId(): String {
        return (1..STRING_LENGTH)
            .map { Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }

    override fun createSize(): Array<String> {
        val sizeArray = arrayOf("0", "0")
        sizeArray[0] = "150"
        sizeArray[1] = "120"

        return sizeArray
    }

    override fun createPoint(): Array<String> {
        val pointArray = arrayOf("0", "0")
        pointArray[0] = (1..600).random().toString()
        pointArray[1] = (1..600).random().toString()

        return pointArray
    }

    override fun createRGB(): Array<String> {
        val rgbArray = arrayOf("0", "0", "0")
        rgbArray[0] = (0..255).random().toString()
        rgbArray[1] = (0..255).random().toString()
        rgbArray[2] = (0..255).random().toString()

        return rgbArray
    }

    override fun createAlpha(): String {
        return (1..10).random().toString()
    }
}

package com.example.kotlin_drawingapp.square

import kotlin.random.Random

class ID {
    private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    companion object {
        const val STRING_LENGTH = 3
    }

    fun createSeparatedId(): String {
        return (1..STRING_LENGTH)
            .map { Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }

    fun createID(): String {
        val firstIdSeparate = createSeparatedId()
        val secondIdSeparate = createSeparatedId()
        val thirdSeparate = createSeparatedId()
        val idResult = arrayOf(firstIdSeparate, secondIdSeparate, thirdSeparate)
        return idResult.joinToString("-")
    }
}
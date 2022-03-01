package com.example.myapplication.Class

class NumberFactory {
    private fun getRandomString(length: Int): String {
        val chars = ('a'..'z') + (0..9)
        return (1..length)
            .map { chars.random() }
            .joinToString("")
    }

    fun getIdValue(): String {
        return "${getRandomString(3)}-${getRandomString(3)}-${getRandomString(3)}"
    }

    fun getRandomPointNumber(): Int {
        return (1..999).random()
    }

    fun getRandomRgbNumber(): Int {
        return (1..255).random()
    }

    fun getRandomAlphaNumber(): Int {
        return (1..9).random()
    }
}
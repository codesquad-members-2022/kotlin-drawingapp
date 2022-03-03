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

    fun getRandomYPointNumber(): Int {
        return (1..550).random()
    }

    fun getRandomXPointNumber(): Int {
        return (1..1000).random()
    }

    fun getRandomRgbNumber(): Int {
        return (1..255).random()
    }

    private fun getRandomAlphaNumber(): Int {
        return (1..9).random()
    }

    fun getRandomAlphaFloat() : Float{
        return when(getRandomAlphaNumber()){
            1 -> 0.1f
            2 -> 0.2f
            3 -> 0.3f
            4 -> 0.4f
            5 -> 0.5f
            6 -> 0.6f
            7 -> 0.7f
            8 -> 0.8f
            else -> 0.9f
        }
    }
}
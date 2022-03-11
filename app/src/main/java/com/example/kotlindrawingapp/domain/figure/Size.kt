package com.example.kotlindrawingapp.domain.figure

data class Size(
    val width: Int = DEFAULT_WIDTH,
    val height: Int = DEFAULT_HEIGHT
) {

    companion object {
        private const val DEFAULT_WIDTH = 150
        private const val DEFAULT_HEIGHT = 120
    }
}
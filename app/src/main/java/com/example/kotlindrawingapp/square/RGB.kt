package com.example.kotlindrawingapp.square

data class RGB(
    val red: Int,
    val green: Int,
    val blue: Int
) {

    init {
        check(isRange(red) && isRange(green) && isRange(blue)) { OUT_OF_RANGE_MESSAGE }
    }

    private fun isRange(color: Int): Boolean {
        return color in MINIMUM..MAXIMUM
    }

    fun decimalToHex(): String {
        val red = Integer.toHexString(red)
        val green = Integer.toHexString(green)
        val blue = Integer.toHexString(blue)
        return red + green + blue
    }

    companion object {
        private const val MINIMUM = 0
        private const val MAXIMUM = 255
        private const val OUT_OF_RANGE_MESSAGE = "범위에서 벗어났습니다."
    }
}

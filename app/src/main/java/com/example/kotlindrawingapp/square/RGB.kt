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
        var red = Integer.toHexString(red)
        var green = Integer.toHexString(green)
        var blue = Integer.toHexString(blue)
        if (red.length == 1) red = "0$red"
        if (green.length == 1) green = "0$green"
        if (blue.length == 1) blue = "0$blue"
        return "#$red$green$blue"
    }

    companion object {
        private const val MINIMUM = 0
        private const val MAXIMUM = 255
        private const val OUT_OF_RANGE_MESSAGE = "범위에서 벗어났습니다."
    }
}

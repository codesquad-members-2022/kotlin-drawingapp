package com.example.kotlindrawingapp.square

data class Alpha(val alpha: Int) {

    init {
        check(isRange(alpha)) { OUT_OF_RANGE_MESSAGE }
    }

    private fun isRange(alpha: Int): Boolean {
        return alpha in MINIMUM..MAXIMUM
    }

    companion object {
        private const val MINIMUM = 1
        private const val MAXIMUM = 10
        private const val OUT_OF_RANGE_MESSAGE = "범위에서 벗어났습니다."
    }
}

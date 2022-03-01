package com.example.kotlindrawingapp.square

data class Square(
    private val id: ID,
    val point: Point,
    val size: Size,
    private val rgb: RGB,
    private val alpha: Alpha
) {
    override fun toString(): String {
        return "Rect (${id.id}), X:${point.x},Y:${point.y} W${size.width}, H${size.height}, R:${rgb.red}, G:${rgb.green}, B:${rgb.blue}, Alpha: ${alpha.alpha}"
    }
}
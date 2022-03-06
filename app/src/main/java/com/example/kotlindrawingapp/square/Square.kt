package com.example.kotlindrawingapp.square

data class Square(
    private val id: ID,
    val point: Point,
    val size: Size,
    var rgb: RGB,
    var alpha: Alpha
) {

    fun isSelected(touchPoint: Point): Boolean {
        return touchPoint.x >= point.x && touchPoint.y >= point.y
                && touchPoint.x <= point.x + size.width && touchPoint.y <= point.y + size.height
    }

    fun update(rgb: RGB) {
        this.rgb = rgb
    }

    fun update(alpha: Alpha) {
        this.alpha = alpha
    }

    override fun toString(): String {
        return "Rect (${id.id}), X:${point.x},Y:${point.y} W${size.width}, H${size.height}, R:${rgb.red}, G:${rgb.green}, B:${rgb.blue}, Alpha: ${alpha.alpha}"
    }
}
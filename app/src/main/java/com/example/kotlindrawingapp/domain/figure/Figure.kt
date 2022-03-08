package com.example.kotlindrawingapp.domain.figure

abstract class Figure {
    abstract val id: ID
    abstract val point: Point
    abstract val size: Size
    abstract var alpha: Alpha
    abstract var rgb: RGB?

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
}
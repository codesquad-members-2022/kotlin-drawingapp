package com.example.kotlindrawingapp.domain.figure

abstract class Figure {
    abstract val id: ID
    abstract var point: Point
    abstract var size: Size
    abstract var alpha: Alpha
    abstract var rgb: RGB?

    open fun isSelected(touchPoint: Point): Boolean {
        return touchPoint.x >= point.x && touchPoint.y >= point.y
                && touchPoint.x <= point.x + size.width && touchPoint.y <= point.y + size.height
    }

    fun update(rgb: RGB) {
        this.rgb = rgb
    }

    fun update(alpha: Alpha) {
        this.alpha = alpha
    }

    fun update(point: Point) {
        this.point = point
    }

    fun update(size: Size) {
        this.size = size
    }
}
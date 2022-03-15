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
}
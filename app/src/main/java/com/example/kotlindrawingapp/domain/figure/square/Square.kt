package com.example.kotlindrawingapp.domain.figure.square

import com.example.kotlindrawingapp.domain.figure.*

data class Square(
    override val id: ID,
    override var point: Point,
    override val size: Size,
    override var rgb: RGB?,
    override var alpha: Alpha
) : Figure() {

    override fun toString(): String {
        return "Rect (${id.id}), X:${point.x},Y:${point.y} W${size.width}, H${size.height}, R:${rgb?.red}, G:${rgb?.green}, B:${rgb?.blue}, Alpha: ${alpha.alpha}"
    }
}
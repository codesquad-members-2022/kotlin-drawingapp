package com.codesquad_han.kotlin_drawingapp.model

class Rectangle(id: String, point: Point, size: Size, backgroundColor: BackgroundColor, transparency: Transparency)  {
    private var id = id
    private var point = point
    private var size = size
    private var backgroundColor = backgroundColor
    private var transparency = transparency

    override fun toString(): String {
        return "($id), X:${point.x},Y:${point.y}, W:${size.width},H:${size.height}, R:${backgroundColor.r},G:${backgroundColor.g},${backgroundColor.b}, " +
                "Alpha:${transparency.transparency}"
    }
}
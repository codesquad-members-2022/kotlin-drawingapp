package com.codesquad_han.kotlin_drawingapp.rectangle

interface RectangleViewClickInterface {
    fun clickDrawingView(color: String, alpha: Int, selected: Boolean, id: String, x: Int, y: Int, width: Int, height: Int)
    fun updatePointText(x: Int, y: Int)
    fun updateSelectedRectanglePoint(id: String, newX : Int, newY : Int)
}
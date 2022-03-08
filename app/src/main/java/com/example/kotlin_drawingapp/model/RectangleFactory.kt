package com.example.kotlin_drawingapp.model

import com.example.kotlin_drawingapp.model.draw.DrawObject

class RectangleFactory : Factory() {
    companion object {
        fun create(): DrawObject.Rectangle {
            return DrawObject.Rectangle(randomId(), randomSize(), randomPoint(), randomColor(), randomAlpha())
        }
    }
}
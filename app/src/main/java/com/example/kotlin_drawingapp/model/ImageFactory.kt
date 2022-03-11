package com.example.kotlin_drawingapp.model

import android.graphics.Bitmap
import com.example.kotlin_drawingapp.model.draw.DrawObject

class ImageFactory : Factory() {
    companion object {
        fun create(bitmap: Bitmap): DrawObject.Image {
            return DrawObject.Image(
                randomId(),
                randomSize(),
                randomPoint(),
                randomAlpha(),
                bitmap
            )
        }
    }
}
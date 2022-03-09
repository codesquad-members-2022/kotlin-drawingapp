package com.codesquad_han.kotlin_drawingapp.data

import android.net.Uri
import com.codesquad_han.kotlin_drawingapp.model.Rectangle

interface RectangleRepository {

    fun addRectangle()

    fun getRectangleList() : MutableList<Rectangle>

    fun updateTransparency(id: String, transparency: Int)

    fun updateImageUri(id: String, imageUri: Uri?)

    fun updateSelctedRectanglePoint(id: String, newX: Int, newY: Int)
}
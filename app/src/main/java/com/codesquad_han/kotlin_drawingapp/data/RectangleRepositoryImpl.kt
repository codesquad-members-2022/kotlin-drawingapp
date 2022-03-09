package com.codesquad_han.kotlin_drawingapp.data

import android.net.Uri
import com.codesquad_han.kotlin_drawingapp.model.Plane
import com.codesquad_han.kotlin_drawingapp.model.Rectangle

class RectangleRepositoryImpl(var plane: Plane) : RectangleRepository {
    override fun addRectangle() {
        plane.generateRectangle()
    }

    override fun getRectangleList() = plane.returnRectangleList()

    override fun updateTransparency(id: String, transparency: Int) {
        plane.updateTransparency(id, transparency)
    }

    override fun updateImageUri(id: String, imageUri: Uri?) {
        plane.updateImageUri(id, imageUri)
    }

    override fun updateSelctedRectanglePoint(id: String, newX: Int, newY: Int) {
        plane.updateSelectedRectanglePoint(id, newX, newY)
    }

    override fun updatePointX(value: Int, id: String): Int {
        return plane.updatePointX(value, id)
    }

    override fun updatePointY(value: Int, id: String): Int {
        return plane.updatePointY(value, id)
    }
}
package com.codesquad_han.kotlin_drawingapp.model

import android.net.Uri

class Plane(rectangleFactory: RectangleFactory) {
    private var rectangleList = mutableListOf<Rectangle>()
    private var rectangleFactory = rectangleFactory

    fun generateRectangle() {
        val rectangle = rectangleFactory.generateRectangle()
        rectangleList.add(rectangle)
    }

    fun returnRectangleList() = rectangleList

    fun updateTransparency(id: String, transparency: Int) {
        rectangleList.forEach {
            if (it.id == id) {
                it.transparency.transparency = transparency
            }
        }
    }

    fun updateSelectedRectanglePoint(id: String, newX: Int, newY: Int){
        rectangleList.forEach {
            if (it.id == id) {
                it.point.x = newX
                it.point.y = newY
            }
        }
    }

    fun updateImageUri(id: String, imageUri: Uri?) {
        rectangleList.forEach {
            if(it.id == id){
                it.imageUri = imageUri
            }
        }
    }

    fun returnRectangleCount() = rectangleList.size
}
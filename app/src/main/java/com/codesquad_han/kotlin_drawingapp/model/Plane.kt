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

    fun updateImageUri(id: String, imageUri: Uri?) {
        rectangleList.forEach {
            if(it.id == id){
                it.imageUri = imageUri
            }
        }
    }

    fun returnRectangleCount() = rectangleList.size
}
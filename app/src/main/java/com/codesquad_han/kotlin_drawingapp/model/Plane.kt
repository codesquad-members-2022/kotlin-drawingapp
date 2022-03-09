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

    fun updatePointX(value: Int, id: String): Int{
        rectangleList.forEach {
            if(it.id == id){
                if(it.point.x > 1) it.point.x += value
                return it.point.x
            }
        }
        return -1
    }

    fun updatePointY(value: Int, id: String): Int{
        rectangleList.forEach {
            if(it.id == id){
                if(it.point.y > 1) it.point.y += value
                return it.point.y
            }
        }
        return -1
    }

    fun updateSizeWidth(value: Int, id: String): Int{
        rectangleList.forEach {
            if(it.id == id){
                if(it.size.width > 1) it.size.width += value
                return it.size.width
            }
        }
        return -1
    }

    fun updateSizeHeight(value: Int, id: String): Int{
        rectangleList.forEach {
            if(it.id == id){
                if(it.size.height > 1) it.size.height += value
                return it.size.height
            }
        }
        return -1
    }

    fun returnRectangleCount() = rectangleList.size
}
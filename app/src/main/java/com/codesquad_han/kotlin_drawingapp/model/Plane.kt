package com.codesquad_han.kotlin_drawingapp.model

import android.net.Uri
import com.codesquad_han.kotlin_drawingapp.model.rectangle.BaseRectangle

class Plane(rectangleFactory: RectangleFactory) {
    private var rectangleList = mutableListOf<BaseRectangle>()
    private var rectangleFactory = rectangleFactory
    private var normalRectangleCount = 1
    private var imageRectangleCount = 1
    private var textRectangleCount = 1


    fun generateNormalRectangle() {
        val rectangle = rectangleFactory.generateNormalRectangle()
        rectangle.createNum = normalRectangleCount++
        rectangleList.add(rectangle)
    }

    fun generateTextRectangle(){
        val textRectangle = rectangleFactory.generateTextRectangle()
        textRectangle.createNum = textRectangleCount++
        rectangleList.add(textRectangle)
    }

    fun generateImageRectangle(imageUri: Uri?){
        val imageRetangle = rectangleFactory.generateImageRectangle(imageUri)
        imageRetangle.createNum = imageRectangleCount++
        rectangleList.add(imageRetangle)
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

    fun updateSelectedRectangle(id: String, isSelectedExist: Boolean){
        if(isSelectedExist){
            rectangleList.forEach {
                it.selected = it.id == id
            }
        }
        else{
            rectangleList.forEach {
                it.selected = false
            }
        }
    }

    fun getSelectedRectangle(id: String): BaseRectangle?{
        rectangleList.forEach {
            if(it.id == id) return it
        }
        return null
    }

    fun returnRectangleCount() = rectangleList.size
}
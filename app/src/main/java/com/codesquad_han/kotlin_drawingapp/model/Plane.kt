package com.codesquad_han.kotlin_drawingapp.model

import android.widget.ImageView

class Plane(rectangleFactory: RectangleFactory) {
    private var rectangleArrayList = ArrayList<RectangleImageviewData>()
    private var rectangleFactory = rectangleFactory

    fun generateRectangle() : Rectangle{
        val rectangle = rectangleFactory.generateRectangle()
        rectangleArrayList.add(RectangleImageviewData(rectangle, null, false))
        return rectangle
    }

    fun saveImageView(imageView: ImageView){
        rectangleArrayList[rectangleArrayList.size-1].imageView = imageView
    }

    fun selectImageView(imageView: ImageView) : ArrayList<RectangleImageviewData>{
        rectangleArrayList.forEach {
            it.selected = it.imageView == imageView
        }
        return rectangleArrayList
    }
}
package com.example.myapplication

import com.example.myapplication.Class.Rectangle

class MainPresenter:MainContract.Presenter {

    private var rectangleList = mutableListOf<Rectangle>()

    override fun getRectangleCount(): Int {
        return rectangleList.size
    }

    override fun saveRectangle(rectangle:Rectangle) {
        rectangleList.add(rectangle)
    }
}

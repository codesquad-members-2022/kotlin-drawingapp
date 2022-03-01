package com.example.myapplication

import com.example.myapplication.Class.Rectangle

interface MainContract {

    interface View{

        fun getRectangle(index:Int) : Rectangle
    }

    interface Presenter{

        fun getRectangleCount():Int

        fun saveRectangle(rectangle: Rectangle)
    }
}
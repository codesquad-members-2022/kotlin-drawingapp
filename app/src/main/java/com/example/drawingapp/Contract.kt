package com.example.drawingapp

import android.graphics.PointF
import com.example.drawingapp.data.Rectangle

interface Contract {
    interface View {

        fun getDrawMessage(message: String)

        fun drawRectangle(rectangle: Rectangle)

        fun setColorText(count: Int)
        fun onTouchRectangle(pointF: PointF)
    }

    interface Presenter {

        fun getRectangleLog(): String

        fun onClickLog()

        fun getInput(): InputFactory

        fun getRectangle(): Rectangle

        fun setPlane()

        fun getPlaneData()
    }

    interface Repository {

        fun getInputFactory(): InputFactory

        fun getRectangle(inputFactory: InputFactory): Rectangle

        fun getRectangleLog(rectangle: Rectangle): String

        fun setPlane(rectangle: Rectangle)

        fun getPlane(index: Int): Rectangle

        fun getPlaneCount(): Int
    }
}
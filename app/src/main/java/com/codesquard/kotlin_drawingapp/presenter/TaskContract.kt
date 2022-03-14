package com.codesquard.kotlin_drawingapp.presenter

import android.graphics.Bitmap
import com.codesquard.kotlin_drawingapp.model.Rectangle

interface TaskContract {

    interface TaskView {

        fun showRectangle(newRect: Rectangle)

        fun showSelectedRectangle()

        fun enableRectColorBtnAndSlider()

        fun showRectColor(color: String = "")

        fun showRectAlpha(alpha: Float = 0f)

        fun showEnabledColor(boolean: Boolean)

        fun showDraggingRectangle(tempRect: Rectangle?)

        fun showRectSize(width: Float = 0f, height: Float = 0f)

        fun showRectPosition(x: String = "", y: String = "")

        fun measureTextSize(textRect: Rectangle)

    }

    interface Presenter {

        fun addNewRectangle(width: Float, height: Float, photo: Bitmap? = null)

        fun createNewTextRectangle()

        fun addNewTextRectangle(textRect: Rectangle, textSize: Array<Int>)

        fun selectRectangle(x: Float, y: Float)

        fun changeAlphaValue(value: Float)

        fun changeColor()

        fun changeSize(x: Float, y: Float, isWidth: Boolean)

        fun changePosition(x: Float, y: Float, isX: Boolean)

        fun dragRectangle(x: Float, y: Float)

    }

}
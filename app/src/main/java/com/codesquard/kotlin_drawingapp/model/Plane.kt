package com.codesquard.kotlin_drawingapp.model

import android.graphics.Bitmap
import com.codesquard.kotlin_drawingapp.presenter.RectangleListener

class Plane(private val listener: RectangleListener) {

    private val rectangleList = mutableListOf<Rectangle>()
    private lateinit var newRect: Rectangle
    private var selectedRect: Rectangle? = null

    fun createNewRectangle(width: Float, height: Float, photo: Bitmap? = null) {
        photo?.run {
            val photoRect = PhotoRectangle()
            photoRect.setBitmap(this)
            newRect = photoRect
        } ?: run {
            newRect = NormalRectangle()
        }
        newRect = RectangleFactory(newRect).getInstance()
        newRect.setSize(width.toInt(), height.toInt())
        rectangleList.add(newRect)
        listener.onCreateRectangle(newRect)
    }

    fun getRectangle(index: Int) = rectangleList[index]

    fun countRectangle() = rectangleList.size

    fun selectRectangle(x: Float, y: Float) {
        unSelectRectangle()

        val reversedRectList = rectangleList.reversed()
        reversedRectList.forEachIndexed { index, rect ->
            val rectFirstX = rect.point[0]
            val rectFirstY = rect.point[1] + 45
            val rectSecondX = rectFirstX + rect.size[0]
            val rectSecondY = rectFirstY + rect.size[1]
            val notReversedListIndex = reversedRectList.size - 1 - index

            if ((x in rectFirstX..rectSecondX) && (y in rectFirstY..rectSecondY)) {
                rect.isSelected(true)
                selectedRect = rectangleList[notReversedListIndex]
                listener.onSelectRectangle(notReversedListIndex)
                return
            } else {
                listener.onUnSelectRectangle()
            }
        }
    }

    private fun unSelectRectangle() {
        selectedRect?.run {
            this.isSelected(false)
            selectedRect = null
        }
        listener.onUnSelectRectangle()
    }

    fun updateAlpha(value: Float) {
        selectedRect?.apply {
            this.setAlpha((value * 25).toInt())
            listener.onUpdateRectangle()
        } ?: return
    }

    fun updateColor() {
        val r = (0..255).random()
        val g = (0..255).random()
        val b = (0..255).random()
        selectedRect?.apply {
            this.setColor(r, g, b)
            listener.onUpdateRectangle()
        } ?: return
    }

    fun reduceSize(isWidth: Boolean) {
        selectedRect?.let {
            if (isWidth && it.size[0] > 1) {
                it.size[0] -= 1
            } else if (!isWidth && it.size[1] > 1) {
                it.size[1] -= 1
            } else return
        } ?: return
        listener.onUpdateRectangle()
    }

    fun increaseSize(isWidth: Boolean) {
        selectedRect?.let {
            if (isWidth) {
                it.size[0] += 1
            } else {
                it.size[1] += 1
            }
        } ?: return
        listener.onUpdateRectangle()
    }

    fun reducePosition(isX: Boolean) {
        selectedRect?.let {
            if (isX && it.point[0] >= 2f) {
                it.point[0] -= 1f
            } else if (!isX && it.point[1] >= 2f) {
                it.point[1] -= 1f
            } else return
        } ?: return
        listener.onUpdateRectangle()
    }

    fun increasePosition(isX: Boolean) {
        selectedRect?.let {
            if (isX) {
                it.point[0] += 1f
            } else {
                it.point[1] += 1f
            }
        } ?: return
        listener.onUpdateRectangle()
    }

    fun dragRectangle(x: Float, y: Float) {
        selectedRect?.run {
            this.point[0] = x
            this.point[1] = y - 45
            if (this.point[0] < 1) {
                this.point[0] = 1f
            }
            if (this.point[1] < 1) {
                this.point[1] = 1f
            }
        } ?: return
        listener.onDragRectangle(selectedRect)
    }
}
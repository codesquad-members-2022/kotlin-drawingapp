package com.codesquard.kotlin_drawingapp.model

import android.graphics.Bitmap
import com.codesquard.kotlin_drawingapp.presenter.RectangleListener

class Plane(private val listener: RectangleListener) {

    private val rectangleList = mutableListOf<Rectangle>()
    private var selectedRect: Rectangle? = null
    private lateinit var rectInitSize: Array<Int>
    private lateinit var rectMaxPoint: Array<Int>
    private val draggedPoint: Array<Float> = arrayOf(0f, 0f)

    fun setInitRectSizeAndMaxPoint(rectSize: Array<Int>, rectMaxPoint: Array<Int>) {
        this.rectInitSize = rectSize
        this.rectMaxPoint = rectMaxPoint
    }

    fun createNewPhotoRectangle(photo: Bitmap) {
        val newRect = RectangleFactory(PhotoRectangle()).getInstance(rectInitSize, rectMaxPoint, photo)
        rectangleList.add(newRect)
        listener.onCreateRectangle(newRect)
    }

    fun createNewNormalRectangle() {
        val newRect = RectangleFactory(NormalRectangle()).getInstance(rectInitSize, rectMaxPoint)
        rectangleList.add(newRect)
        listener.onCreateRectangle(newRect)
    }

    fun createNewTextRectangle() {
        val newRect = RectangleFactory(TextRectangle()).getInstance(rectInitSize, rectMaxPoint)
        listener.onMeasureTextSize(newRect)
    }

    fun addNewTextRectangle(textRect: Rectangle, textSize: Array<Int>) {
        textRect.setSize(textSize[0], textSize[1])
        rectangleList.add(textRect)
        listener.onCreateRectangle(textRect)
    }

    fun getRectangle(index: Int) = rectangleList[index]

    fun countRectangle() = rectangleList.size

    fun selectRectangle(x: Float, y: Float) {
        unSelectRectangle()

        val reversedRectList = rectangleList.reversed()
        reversedRectList.forEachIndexed { index, rect ->
            val rectFirstX = rect.point[0]
            val rectFirstY = rect.point[1]
            val rectSecondX = rectFirstX + rect.size[0]
            val rectSecondY = rectFirstY + rect.size[1]
            val notReversedListIndex = reversedRectList.size - 1 - index

            if ((x in rectFirstX..rectSecondX) && (y in rectFirstY..rectSecondY)) {
                rect.isSelected(true)
                setTouchedPoint(x, y)
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
            if (isX && it.point[0] < (rectMaxPoint[0] - it.size[0] - 5)) {
                it.point[0] += 1f
            } else if (!isX && it.point[1] < (rectMaxPoint[1] - it.size[1] - 5)) {
                it.point[1] += 1f
            }
        } ?: return
        listener.onUpdateRectangle()
    }

    private fun setTouchedPoint(x: Float, y: Float) {
        draggedPoint[0] = x
        draggedPoint[1] = y
    }

    private fun setDraggedPoint(x: Float, y: Float) {
        draggedPoint[0] = x - draggedPoint[0]
        draggedPoint[1] = y - draggedPoint[1]
    }

    fun dragRectangle(x: Float, y: Float) {
        setDraggedPoint(x, y)
        selectedRect?.run {
            this.point[0] += draggedPoint[0]
            this.point[1] += draggedPoint[1]
            if (this.point[0] < 1) {
                this.point[0] = 1f
            }
            if (this.point[1] < 1) {
                this.point[1] = 1f
            }
            if (this.point[0] + this.size[0] >= rectMaxPoint[0]) {
                this.point[0] = rectMaxPoint[0] - this.size[0].toFloat() - 5
            }
            if (this.point[1] + this.size[1] >= rectMaxPoint[1]) {
                this.point[1] = rectMaxPoint[1] - this.size[1].toFloat() - 5
            }
        } ?: return
        setTouchedPoint(x, y)
        listener.onDragRectangle(selectedRect)
    }
}
package com.codesquard.kotlin_drawingapp

class Plane(private val listener: RectangleListener) {

    private val rectangleList = mutableListOf<Rectangle>()

    fun createNewRectangle() {
        rectangleList.add(RectangleFactory().getInstance())
        val newRect = rectangleList[rectangleList.size - 1]
        listener.onCreateRectangle(newRect)
    }

    fun selectRectangle(x: Float, y: Float) {
        val reversedRectList = rectangleList.reversed()
        reversedRectList.forEach {
            it.isSelected()
            listener.onSelectRectangle()
        }
        reversedRectList.forEachIndexed { index, rect ->
            val rectFirstX = rect.getPoint()[0] + 44
            val rectFirstY = rect.getPoint()[1] + 7
            val rectSecondX = rectFirstX + rect.getSize()[0]
            val rectSecondY = rectFirstY + rect.getSize()[1]
            val notReversedIndex = reversedRectList.size - 1 - index

            if ((x in rectFirstX..rectSecondX) && (y in rectFirstY..rectSecondY)) {
                rect.isSelected(true)
                listener.onSelectRectangle(notReversedIndex)
                return
            } else {
                listener.onSelectRectangle()
            }
        }
    }

    fun getRectangle(index: Int) = rectangleList[index]

    fun updateAlpha(value: Float) {
        rectangleList.forEach {
            if (it.getStatus()) {
                it.setAlpha((value * 25).toInt())
                listener.onUpdateRectangle()
                return
            }
        }
    }

    fun updateColor() {
        val r = (0..255).random()
        val g = (0..255).random()
        val b = (0..255).random()
        rectangleList.forEach {
            if (it.getStatus()) {
                it.setColor(r, g, b)
                listener.onUpdateRectangle()
                return
            }
        }
    }
}
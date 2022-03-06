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
        }
        reversedRectList.forEach {
            val rectFirstX = it.getPoint()[0] + 44
            val rectFirstY = it.getPoint()[1] + 7
            val rectSecondX = rectFirstX + it.getSize()[0]
            val rectSecondY = rectFirstY + it.getSize()[1]

            if ((x in rectFirstX..rectSecondX) && (y in rectFirstY..rectSecondY)) {
                it.isSelected(true)
                listener.onSelectRectangle()
                return
            }
        }
    }
}
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
            listener.onSelectNoRectangle()
        }
        reversedRectList.forEachIndexed { index, rect ->
            val rectFirstX = rect.getPoint()[0] + 44
            val rectFirstY = rect.getPoint()[1] + 7
            val rectSecondX = rectFirstX + rect.getSize()[0]
            val rectSecondY = rectFirstY + rect.getSize()[1]

            if ((x in rectFirstX..rectSecondX) && (y in rectFirstY..rectSecondY)) {
                rect.isSelected(true)
                listener.onSelectRectangle(index)
                return
            }
        }
    }
}
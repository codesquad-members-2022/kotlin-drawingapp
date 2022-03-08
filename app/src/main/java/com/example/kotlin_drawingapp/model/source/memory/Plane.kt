package com.example.kotlin_drawingapp.model.source.memory

import com.example.kotlin_drawingapp.model.draw.DrawObject

class Plane : PlaneDao {
    private val drawObjectList = mutableListOf<DrawObject>()

    override fun getDrawObjectCount(): Int {
        return drawObjectList.size
    }

    override fun saveDrawObject(drawObject: DrawObject) {
        drawObjectList.add(drawObject)
    }

    override fun saveSelectedStatus(drawObject: DrawObject, selected: Boolean) {
        for (obj in drawObjectList) {
            if (obj == drawObject) {
                obj.selected = selected
                return
            }
        }
    }

    override fun clearDrawObjectBorder() {
        for (drawObject in drawObjectList) {
            drawObject.selected = false
        }
    }

    override fun getAllDrawObject(): List<DrawObject> {
        return drawObjectList.toList()
    }

    override fun modifyDrawObject(target: DrawObject, replacement: DrawObject) {
        for (index in 0 until drawObjectList.size) {
            if (drawObjectList[index] == target) {
                drawObjectList[index] = replacement
                return
            }
        }
    }

    override fun getDrawObjectByIndex(index: Int): DrawObject {
        return drawObjectList[index]
    }

    override fun getDrawObjectByPosition(x: Int, y: Int): DrawObject? {
        for (index in drawObjectList.size-1 downTo 0) {
            if (drawObjectList[index].currentPoint.x <= x && drawObjectList[index].currentPoint.x + drawObjectList[index].currentSize.width >= x &&
                drawObjectList[index].currentPoint.y <= y && drawObjectList[index].currentPoint.y + drawObjectList[index].currentSize.height >= y
            ) {
                return drawObjectList[index]
            }
        }

        return null
    }
}

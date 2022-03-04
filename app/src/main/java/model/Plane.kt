package model

import android.content.Context
import view.RectView

class Plane(private val context: Context) {
    private val customRectangleList: ArrayList<Rect> = arrayListOf()

    fun createRectanglePaint(): Rect {
        val rect = RectFactory.makeRect()
        customRectangleList.add(rect)
        return rect
    }

    fun getRectCount(): Int {
        return customRectangleList.size
    }

    fun getCustomRectangleByPosition(x: Float, y: Float): Rect? {
        val indexOfRectangle = checkIsInCustomRectangleArea(x, y)
        return if (indexOfRectangle == -1) {
            null
        } else {
            customRectangleList[indexOfRectangle]
        }
    }

    fun checkIsInCustomRectangleArea(x: Float, y: Float): Int {
        customRectangleList.map {
            if (it.point.xPos <= x && it.point.xPos + it.size.width >= x) {
                if (it.point.yPos <= y && it.point.yPos + it.size.height > y) {
                    return customRectangleList.indexOf(it)
                }
            }
        }
        return -1
    }

    fun getRectByIndex(index: Int): Rect {
        return customRectangleList[index]
    }

    fun changeColor(rect: Rect){
        val randomColor = BackGroundColor((0..255).random(), (0..255).random(), (0..255).random())
        customRectangleList.find { it == rect }?.backGroundColor?.value = randomColor
    }

    fun changeOpacity(rect: Rect, opacity: Int) {
        customRectangleList.find { it == rect }?.opacity?.value = opacity

    }
}
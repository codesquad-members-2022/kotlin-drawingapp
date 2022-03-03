package model

import android.content.Context
import view.RectView

class Plane(private val context: Context) {
    private val customRectangleList: ArrayList<RectView> = arrayListOf()

    fun createRectanglePaint(): RectView {
        val rect = RectFactory.makeRect()
        val rectView = RectView(context,rect)
        customRectangleList.add(rectView)
        return rectView
    }

    fun getRectCount(): Int {
        return customRectangleList.size
    }

    fun getCustomRectangleByPosition(x: Float, y: Float): Rect? {
        val indexOfRectangle = checkIsInCustomRectangleArea(x, y)
        return if (indexOfRectangle == -1) {
            null
        } else {
            customRectangleList[indexOfRectangle].rect
        }
    }

    fun getCustomRectangleViewByPosition(x: Float, y: Float): RectView? {
        val indexOfRectangle = checkIsInCustomRectangleArea(x, y)
        return if (indexOfRectangle == -1) {
            null
        } else {
            customRectangleList[indexOfRectangle]
        }
    }

    fun checkIsInCustomRectangleArea(x: Float, y: Float): Int {
        customRectangleList.map {
            if (it.rect.point.xPos <= x && it.rect.point.xPos + it.rect.size.width >= x) {
                if (it.rect.point.yPos <= y && it.rect.point.yPos + it.rect.size.height > y) {
                    return customRectangleList.indexOf(it)
                }
            }
        }
        return -1
    }

    fun getRectByIndex(index: Int): Rect {
        return customRectangleList[index].rect
    }

    fun changeColor(rectView: RectView): BackGroundColor {
        val randomColor = BackGroundColor((0..255).random(), (0..255).random(), (0..255).random())
        customRectangleList.find { it== rectView }?.rect?.backGroundColor?.value = randomColor
        return randomColor
    }

    fun changeOpacity(rectView: RectView, opacity: Int) {
        customRectangleList.find { it== rectView }?.rect?.opacity?.value=  opacity

    }
}
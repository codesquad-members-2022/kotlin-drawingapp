package view

import model.Rect
import android.widget.TextView
import model.BackGroundColor

import model.RectView

interface MainContract {
    interface View{
        fun drawRectangle(rectView: RectView)
        fun drawBorder(rectView: RectView?)
        fun displayAttribute(rect: Rect)
        fun showColorChange(rectView: RectView, color:BackGroundColor, colorValueView: TextView)
        fun showOpacityChange(rectView: RectView, opacity: Int)
    }

    interface Presenter{
        fun selectRectangle(xPos:Float, yPos:Float)
        fun changeColor(rectView:RectView, colorValueView:TextView)
        fun changeOpacity(rectView: RectView, opacity:Int)
        fun createRectanglePaint()

    }
}
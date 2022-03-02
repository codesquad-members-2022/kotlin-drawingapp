package view

import model.Rect
import android.widget.ImageView
import android.widget.TextView
import model.BackGroundColor

interface MainContract {
    interface View{
        fun showSelected(rect: Rect, rectView: ImageView?, border: ImageView?)
        fun showColorChange(rectView: ImageView, color:BackGroundColor, colorValueView: TextView)
        fun showOpacityChange(rectView: ImageView, opacity: Int)
    }

    interface Presenter{
        fun createRectangle():ImageView
        fun selectRectangle(xPos:Float, yPos:Float)
        fun changeColor(rectView:ImageView, colorValueView:TextView)
        fun changeOpacity(rectView: ImageView, opacity:Int)
    }
}
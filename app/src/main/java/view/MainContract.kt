package view

import Rect
import android.widget.ImageView

interface MainContract {
    interface View{
        fun showSelected(rect: Rect, rectView: ImageView?, border: ImageView?)
    }

    interface Presenter{
        fun createRectangle():ImageView
        fun selectRectangle(xPos:Float, yPos:Float)
    }
}
package view

import model.Rect

interface MainContract {
    interface View {
        fun displayAttribute(rectView: RectView)
    }

    interface Presenter {
        fun selectRectangle(xPos: Float, yPos: Float)
        fun changeColor(rectView: RectView)
        fun changeOpacity(rectView: RectView, opacity: Int)
        fun createRectanglePaint(): RectView

    }
}
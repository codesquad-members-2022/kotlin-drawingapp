package view

import android.graphics.Bitmap


interface MainContract {
    interface View {
        fun displayAttribute(rectView: RectView)
    }

    interface Presenter {
        fun selectRectangle(xPos: Float, yPos: Float)
        fun changeColor(rectView: RectView)
        fun changeOpacity(rectView: RectView, opacity: Int)
        fun createRectanglePaint(): RectView
        fun createPhotoPaint(image: Bitmap):RectView
    }
}
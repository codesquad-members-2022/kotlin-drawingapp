package view

import android.graphics.Bitmap
import model.Rect


interface MainContract {
    interface View {
        fun displayRectAttribute(rgbInfo:String,opacity: Int,rect:Rect)
    }

    interface Presenter {
        fun selectRectangle(xPos: Float, yPos: Float):RectView?
        fun changeColor(rectView: RectView)
        fun changeOpacity(rectView: RectView, opacity: Int)
        fun createRectanglePaint(): RectView
        fun createPhotoPaint(image: Bitmap):RectView
    }
}
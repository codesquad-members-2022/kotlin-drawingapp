package view

import android.graphics.Bitmap
import model.Photo
import model.Rect


interface MainContract {
    interface View {
        fun displaySelectedRectAttribute(rect:Rect)
        fun drawRectangle(rect:Rect)
        fun drawPhoto(photo: Photo)
        fun redrawRectangle(rect:Rect)
        fun redrawPhoto(photo: Photo)
    }

    interface Presenter {
        fun selectRectangle(xPos: Float, yPos: Float)
        fun changeColor(rectView: RectView)
        fun changeOpacity(rectView: RectView, opacity: Int)
        fun changePosition(rectView: RectView)
        fun changeXpos(rectView: RectView, value: Int)
        fun changeYPos(rectView: RectView,value: Int)
        fun changeSize(rectView: RectView, mode:String, value:Int)
        fun createRectanglePaint()
        fun createPhotoPaint(image: Bitmap)
    }
}
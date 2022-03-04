package view

import android.graphics.Bitmap
import model.Photo
import model.Rect


interface MainContract {
    interface View {
        fun displaySelectedRectAttribute(rect:Rect)
        fun drawRectangle(rect:Rect)
        fun drawPhoto(photo: Photo)
    }

    interface Presenter {
        fun selectRectangle(xPos: Float, yPos: Float)
        fun changeColor(rectView: RectView)
        fun changeOpacity(rectView: RectView, opacity: Int)
        fun createRectanglePaint()
        fun createPhotoPaint(image: Bitmap)
    }
}
package presenter

import android.graphics.Bitmap
import android.graphics.Canvas
import android.media.Image
import data.ImageRectangle
import data.Rectangle
import data.RegularRectangle
import data.StrokeRectangle

interface Contract {
    interface Presenter {
        fun getRectangles(): MutableList<RegularRectangle>

        fun getRectanglesNumber(): Int

        fun makeStrokes()

        fun getStrokes(): MutableList<StrokeRectangle>

        fun isRectangle(x:Float?, y:Float?): Boolean

        fun removeStrokes()

        fun changeRectangleColor(rectangle: RegularRectangle): MutableList<RegularRectangle>?

        fun getImageRectangle(): MutableList<ImageRectangle>

        fun makeImageRectangle(bitmap: Bitmap)

        fun getRecentClickedRectangle(): RegularRectangle?

        fun getRecentClickedImageRectangle(): ImageRectangle?

        fun getRecentlyClickedObject(): Rectangle?
    }

    interface CustomView {

        fun drawAll(canvas: Canvas?)
    }
}
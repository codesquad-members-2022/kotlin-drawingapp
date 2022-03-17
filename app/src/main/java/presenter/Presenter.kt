package presenter

import android.graphics.Bitmap
import data.*

class Presenter : Contract.Presenter {
    private val plane = Plane()

    override fun getRectangles(): MutableList<RegularRectangle> {
        plane.makeRandomRegularRectangle()
        return plane.getRandomRegularRectangle()
    }

    override fun getRectanglesNumber(): Int {
        return plane.getRectNumber()
    }

    override fun makeStrokes() {
        plane.makeStrokeRectangle()
    }

    override fun getStrokes(): MutableList<StrokeRectangle> {
        return plane.getRectangleStrokesList()
    }

    override fun isRectangle(x: Float?, y: Float?): Boolean {
        return plane.lookupRectangleIsOnThePointer(x, y)
    }

    override fun getRecentClickedRectangle(): RegularRectangle? {
        return plane.getRecentClickedRectangle()
    }

    override fun getRecentClickedImageRectangle(): ImageRectangle? {
        return plane.getRecentClickedImageRectangle()
    }

    override fun getRecentlyClickedObject(): Rectangle? {
        return plane.getRecentlyClickedObject()
    }

    override fun removeStrokes() {
        plane.clearClickedList()
        plane.setStrokesListClear()
    }

    override fun changeRectangleColor(rectangle: RegularRectangle): MutableList<RegularRectangle>? {
        return plane.changeRectangleColorOrNull(rectangle)
    }

    override fun makeImageRectangle(bitmap: Bitmap) {
        plane.setImageRect(bitmap)
    }

    override fun getImageRectangle(): MutableList<ImageRectangle> {
        return plane.getImageRect()
    }
}
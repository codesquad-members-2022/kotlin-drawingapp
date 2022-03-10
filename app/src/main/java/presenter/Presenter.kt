package presenter

import data.Plane
import data.Rectangle

class Presenter : Contract.Presenter {
    private val plane = Plane()

    override fun getRectangles(): MutableList<Rectangle> {
        plane.setRandomRect()
        return plane.getRandomRect()
    }

    override fun getRectanglesNumber(): Int {
        return plane.getRectNumber()
    }

    override fun getStrokes(rectangle: Rectangle): MutableList<Rectangle> {
        plane.setRectangleStrokesList(rectangle)
        return plane.getRectangleStrokesList()
    }

    override fun isRectangle(x: Float?, y: Float?, rectangle: Rectangle): Boolean {
        return plane.onRectangle(x, y, rectangle)
    }

    override fun removeStrokes() {
        plane.setStrokesListClear()
    }

    override fun setRectangleColor(rectangle: Rectangle): MutableList<Rectangle>? {
        return plane.setRectangleColorOrNull(rectangle)
    }
}
package data

class Plane {
    fun setRandomRect() {
        val factory = RectFactory()
        Repository.addRectangle(factory.makeRandomRect())
    }

    fun getRandomRect(): MutableList<Rectangle> {
        return Repository.rectangles
    }

    fun getRectNumber(): Int {
        return Repository.rectangles.count()
    }

    fun getRectangleStrokesList(): MutableList<Rectangle> {
        return Repository.rectangleStrokes
    }

    fun setRectangleStrokesList(rectangle: Rectangle) {
        val stroke = RectFactory()
        val strokeRect = stroke.makeRectangleStrokesList(rectangle)
        Repository.addStroke(strokeRect)
    }

    fun setStrokesListClear() {
        Repository.removeStroke()
    }

    fun setRectangleColorOrNull(rectangle: Rectangle): MutableList<Rectangle>? {
        return Repository.changeRectangleColor(rectangle)
    }

    fun onRectangle(x: Float?, y: Float?, rectangle: Rectangle): Boolean {
        val bottom = rectangle.rectangles.bottom
        val left = rectangle.rectangles.left
        val right = rectangle.rectangles.right
        val top = rectangle.rectangles.top
        val clickX: Float = x.let {
            x as Float
        }
        val clickY: Float = y.let {
            y as Float
        }
        if ((clickX in left..right) && (clickY in top..bottom)) {
            return true
        }
        return false
    }
}
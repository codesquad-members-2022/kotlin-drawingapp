package data

object Repository {
    private var rectanglesList = mutableListOf<Rectangle>()
    val rectangles: MutableList<Rectangle> = rectanglesList

    private var rectangleStrokeLists = mutableListOf<Rectangle>()
    val rectangleStrokes: MutableList<Rectangle> = rectangleStrokeLists

    fun addRectangle(rectangle: Rectangle) {
        rectanglesList.add(rectangle)
    }

    fun removeRectangle(rectangle: Rectangle) {
        rectanglesList.remove(rectangle)
    }

    fun addStroke(rectangle: Rectangle) {
        rectangleStrokeLists.add(rectangle)
    }

    fun removeStroke() {
        rectangleStrokeLists.clear()
    }

    fun changeRectangleColor(rectangle: Rectangle): MutableList<Rectangle>? {
        rectanglesList.forEach { rectangleInTheList ->
            if(rectangleInTheList.id == rectangle.id){
                rectangleInTheList.apply {
                    val r = (0..255).random()
                    val g = (0..255).random()
                    val b = (0..255).random()
                    val a = (200..255).random()
                    this.paint.setARGB(r, g, b, a)
                }
                return rectangles
            }
        }
        return null
    }

}
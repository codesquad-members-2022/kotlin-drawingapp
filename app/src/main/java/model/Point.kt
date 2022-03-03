package model

data class Point(val xPos: Int, val yPos: Int) {
    override fun toString(): String {
        return "X:$xPos,Y:$yPos,"
    }
}
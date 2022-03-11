package model

data class Point(var xPos: Int, var yPos: Int) {
    override fun toString(): String {
        return "X:$xPos,Y:$yPos,"
    }
}
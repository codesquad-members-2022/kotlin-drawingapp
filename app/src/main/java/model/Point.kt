package model

data class Point(val xP:Int, val yP:Int) {
    override fun toString(): String {
        return "X:$xP,Y:$yP,"
    }
}

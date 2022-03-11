package model

data class Size(var width: Int, var height: Int) {
    override fun toString(): String {
        return "W$width, H$height,"
    }
}
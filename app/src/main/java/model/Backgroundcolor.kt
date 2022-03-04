package model

data class Backgroundcolor (val red: Int, val green: Int, val blue: Int) {
    override fun toString(): String {
        return "R:$red, G:$green, B:$blue,"
    }
}

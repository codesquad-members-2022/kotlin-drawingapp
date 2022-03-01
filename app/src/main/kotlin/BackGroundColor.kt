import android.graphics.Color

data class BackGroundColor(val redValue:Int, val greenValue:Int, val blueValue:Int){
    override fun toString(): String {
        return "R:$redValue, G:$greenValue, B:$blueValue,"
    }

    fun getBackGroundColor(): Int {
        return Color.rgb(redValue,greenValue,blueValue)
    }
}

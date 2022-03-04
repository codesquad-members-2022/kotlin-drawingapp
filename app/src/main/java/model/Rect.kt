package model

class Rect(private val rectId:String, private val point:Point, private val size:Size,
           private val backGroundColor: Backgroundcolor, private val opacity:Int) {

    override fun toString(): String {
        return "$rectId, $point $size $backGroundColor Alpha: $opacity"
    }
}
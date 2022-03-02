package model

class Rect(private val rectId:String, val point: Point, val size: Size, var backGroundColor: BackGroundColor, var opacity:Int) {

    override fun toString(): String {
        return "${this.rectId}, $point $size $backGroundColor Alpha: $opacity"
    }
}
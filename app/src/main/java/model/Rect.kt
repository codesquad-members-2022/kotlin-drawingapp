package model

class Rect(rectId:String, val point: Point, val size: Size, var backGroundColor: BackGroundColor, opacity:Int) {
    private var rectId=""
    private var opacity=0

    init {
        this.rectId=rectId
        this.opacity=opacity
    }

    fun getOpacity():Int{
        return this.opacity
    }

    fun setOpacity(opacity: Int){
        this.opacity= opacity
    }

    override fun toString(): String {
        return "${this.rectId}, $point $size $backGroundColor Alpha: $opacity"
    }
}
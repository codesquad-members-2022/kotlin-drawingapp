class Rect(rectId:String, val point:Point, val size:Size, val backGroundColor: BackGroundColor, opacity:Int) {
    private var rectId=""
    private var opacity=0

    init {
        this.rectId=rectId
        this.opacity=opacity
    }

    override fun toString(): String {
        return "${this.rectId}, $point $size $backGroundColor Alpha: $opacity"
    }
}
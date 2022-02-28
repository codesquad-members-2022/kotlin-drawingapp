class Plane {
    private val rectangleList:ArrayList<Rect>  = arrayListOf()

    fun addRectangle(rect:Rect){
        rectangleList.add(rect)
    }


    fun getRectCount():Int{
        return rectangleList.size
    }

    fun getRect(index:Int): Rect {
        return rectangleList[index]
    }

    fun checkIsRectangleIn(xPos:Int, yPos:Int):Boolean{
        rectangleList.map{
            if(xPos in it.point.xPos-75 ..it.point.xPos+75 || yPos in it.point.yPos-60 ..it.point.yPos+60){
                return true
            }
        }
        return false
    }

}
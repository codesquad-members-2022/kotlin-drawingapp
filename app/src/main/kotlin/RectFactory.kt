class RectFactory {

    companion object{
        fun makeRect():Rect{
            return makeNewRectType()
        }

        private fun makeNewRectType():Rect{
            return Rect(randomRectId(), makeRandomPoint(), makeSize(), makeRandomRGB(), makeRandomOpacity())

        }

        private fun makeSize():Size{
            val width= 150
            val height= 120
            return Size(width,height)
        }

        private fun makeRandomPoint():Point{
            val xPos= (1..1024).random()
            val yPos = (1..600).random()
            return Point(xPos,yPos)
        }

        private fun makeRandomOpacity():Int{
            return (1..10).random()
        }

        private  fun makeRandomRGB():BackGroundColor{
            val redValue= (0..255).random()
            val blueValue= (0..255).random()
            val greenValue=(0..255).random()
            return BackGroundColor(redValue,blueValue,greenValue)
        }

        private fun randomRectId():String{
            val targetLength= 3
            return "${getRandomString(targetLength)}-$${getRandomString(targetLength)}-${getRandomString(targetLength)}"
        }

        private fun getRandomString(length: Int) : String {
            val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
            return (1..length)
                .map { allowedChars.random() }
                .joinToString("")
        }
    }
}
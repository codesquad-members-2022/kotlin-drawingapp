package model

import androidx.lifecycle.MutableLiveData
const val textInfo= "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Egestas tellus rutrum tellus pellentesque eu. Viverra justo nec ultrices dui sapien eget mi proin sed. Vel pretium lectus quam id leo. Molestie at elementum eu facilisis sed odio morbi quis commodo. Risus at ultrices mi tempus imperdiet nulla malesuada. In est ante in nibh mauris cursus mattis molestie a. Venenatis urna cursus eget nunc. Eget velit aliquet sagittis id consectetur purus ut. Amet consectetur adipiscing elit pellentesque habitant morbi tristique senectus. Consequat nisl vel pretium lectus quam id. Nisl vel pretium lectus quam id leo in vitae turpis. Purus faucibus ornare suspendisse sed. Amet mauris commodo quis imperdiet."
val textArray= textInfo.split(" ")

class Sentence private constructor(
    rectId: String,
    point: MutableLiveData<Point>,
    size: MutableLiveData<Size>,
    backGroundColor: MutableLiveData<BackGroundColor>,
    val text:String,
    opacity: MutableLiveData<Int> ,
) : Rect(rectId,point,size,backGroundColor,opacity){

    companion object SentenceFactory{

        fun makeSentence():Sentence{
            return Sentence(
                RectFactory.randomRectId(),
                MutableLiveData(RectFactory.makeRandomPoint()),
                MutableLiveData(RectFactory.makeSize()),
                MutableLiveData(RectFactory.makeRandomRGB()),
                getRandomText(),
                MutableLiveData(RectFactory.makeRandomOpacity())
            )
        }

        private fun getRandomText():String{
            var res= ""
            val start= (0..textArray.size-5).random()
            for(i in 0 until 5){
                res+=  "${textArray[start+i]} "
            }
            return res
        }
    }
}
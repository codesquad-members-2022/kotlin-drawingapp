package model

import android.content.Context
import android.graphics.Bitmap
import java.io.ByteArrayOutputStream

class Plane(private val context: Context) {
    private val customRectangleList: ArrayList<Rect> = arrayListOf()

    fun createRectanglePaint(): Rect {
        var rect = RectFactory.makeRect()
        while(customRectangleList.count{it.rectId==rect.rectId }!=0)
        {
            rect= RectFactory.makeRect()
        }
        customRectangleList.add(rect)
        return rect
    }

    fun createPhotoPaint(image: Bitmap): Photo {
        var photo = RectFactory.makePhoto()
        while (customRectangleList.count { it.rectId == photo.rectId } != 0) {
            photo = RectFactory.makePhoto()
        }
        val byteArrayStream = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayStream)
        val imageBytes = byteArrayStream.toByteArray()
        photo.imageInfo = imageBytes
        customRectangleList.add(photo)
        return photo
    }

    fun getRectCount(): Int {
        return customRectangleList.size
    }

    fun getCustomRectangleByPosition(x: Float, y: Float): Rect? {
        val indexOfRectangle = checkIsInCustomRectangleArea(x, y)
        return if (indexOfRectangle == -1) {
            null
        } else {
            customRectangleList[indexOfRectangle]
        }
    }

    fun checkIsInCustomRectangleArea(x: Float, y: Float): Int {
        customRectangleList.map {
            if (it.point.xPos <= x && it.point.xPos + it.size.width >= x) {
                if (it.point.yPos <= y && it.point.yPos + it.size.height > y) {
                    return customRectangleList.indexOf(it)
                }
            }
        }
        return -1
    }

    fun getRectByIndex(index: Int): Rect {
        return customRectangleList[index]
    }

    fun changeColor(rectId:String){
        val randomColor = BackGroundColor((0..255).random(), (0..255).random(), (0..255).random())
        customRectangleList.find { it.rectId == rectId }?.backGroundColor?.value = randomColor
    }

    fun changeOpacity(rectId:String, opacity: Int) {
        customRectangleList.find {  it.rectId == rectId  }?.opacity?.value = opacity

    }

    fun changePosition(rectId: String, x:Int, y:Int):Rect?{
        val selectedRect =   customRectangleList.find {  it.rectId == rectId  }

        selectedRect?.point?.xPos = x
        selectedRect?.point?.yPos= y
        return selectedRect
    }




}
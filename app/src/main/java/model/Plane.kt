package model

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import java.io.ByteArrayOutputStream

class Plane(private val context: Context) {
    private val customRectangleList: ArrayList<Rect> = arrayListOf()

    fun createRectanglePaint(): Rect {
        var rect = RectFactory.makeRect()
        while (customRectangleList.count { it.rectId == rect.rectId } != 0) {
            rect = RectFactory.makeRect()
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

    fun createTextPaint():Sentence{
        var sentence= Sentence.SentenceFactory.makeSentence()
        while (customRectangleList.count { it.rectId == sentence.rectId } != 0) {
            sentence = Sentence.SentenceFactory.makeSentence()
        }
        customRectangleList.add(sentence)
        return sentence
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
            var rectXpos = 0
            var rectYpos = 0
            it.point.value?.let { point ->
                rectXpos = point.xPos
                rectYpos = point.yPos
            }
            it.size.value?.let { size ->
                if (rectXpos <= x && rectXpos + size.width >= x) {
                    if (rectYpos <= y && rectYpos + size.height > y) {
                        return customRectangleList.indexOf(it)
                    }
                }
            }
        }
        return -1
    }

    fun getRectByIndex(index: Int): Rect {
        return customRectangleList[index]
    }

    fun changeColor(rectId: String) {
        val randomColor = BackGroundColor((0..255).random(), (0..255).random(), (0..255).random())
        customRectangleList.find { it.rectId == rectId }?.backGroundColor?.value = randomColor
    }

    fun changeOpacity(rectId: String, opacity: Int) {
        customRectangleList.find { it.rectId == rectId }?.opacity?.value = opacity

    }

    fun changePosition(rectId: String, x: Int, y: Int):Rect? {
        val selectedRect = customRectangleList.find { it.rectId == rectId }
        selectedRect?.point?.value =(Point(x,y))
        return selectedRect
    }

    fun changeSize(rectId: String, width: Int, height: Int) {
        val selectedRect = customRectangleList.find { it.rectId == rectId }
        selectedRect?.size?.value = (Size(width,height))
    }

    fun getCustomRectangleById(rectId: String): Rect? {
        return customRectangleList.find { it.rectId==rectId }
    }
}
package data

import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF

object MakeRandomNumber {

    fun makeRandomID(): String {
        val charA = (97..122).random().toChar()
        val charB = (97..122).random().toChar()
        val charC = (97..122).random().toChar()
        return "$charA$charB$charC"
    }

    //TODO a function of Pixel to DP conversion is needed
    fun makeRandomWidthAndHeight(): RectF {
        return RectF().apply {
            val squareWidth = 250.toFloat()
            val squareHeight = 220.toFloat()
            val x = (0..1000).random().toFloat()
            val y = (0..1000).random().toFloat()
            this.left = x
            this.top = y
            this.right = x + squareWidth
            this.bottom = y + squareHeight
        }
    }

    fun makeRandomRGBA(): Paint {
        return Paint().apply {
            val r = (0..255).random()
            val g = (0..255).random()
            val b = (0..255).random()
            val a = (200..255).random()
            this.setARGB(r, g, b, a)
        }
    }
}
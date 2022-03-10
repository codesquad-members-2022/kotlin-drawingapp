package data

import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF

class RectFactory {
    fun makeRandomRect(): Rectangle {
        val squareWidth = 150.toFloat()
        val squareHeight = 150.toFloat()
        val charA = (97..122).random().toChar()
        val charB = (97..122).random().toChar()
        val charC = (97..122).random().toChar()
        val randomID = "$charA$charB$charC"
        val x = (0..1000).random().toFloat()
        val y = (0..1000).random().toFloat()
        val w = x + squareWidth
        val h = y + squareHeight
        val r = (0..255).random()
        val g = (0..255).random()
        val b = (0..255).random()
        val a = (200..255).random()
        val paint = Paint()
        paint.setARGB(r, g, b, a)
        return Rectangle(randomID, RectF(x, y, w, h), paint)
    }

    fun makeRectangleStrokesList(rectangle: Rectangle): Rectangle {
        val bottom = rectangle.rectangles.bottom
        val left = rectangle.rectangles.left
        val right = rectangle.rectangles.right
        val top = rectangle.rectangles.top
        val paint = Paint()
        paint.style = Paint.Style.STROKE
        paint.color = Color.WHITE
        return Rectangle("Stroke", RectF(left, top, right, bottom), paint)
    }
}
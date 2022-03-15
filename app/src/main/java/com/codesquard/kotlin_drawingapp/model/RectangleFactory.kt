package com.codesquard.kotlin_drawingapp.model

import android.graphics.Bitmap

class RectangleFactory(private val rectangle: Rectangle) {

    private fun setRectangleID() {
        val letter = "abcdefghijklmnopqrstuvwxyz0123456789"
        var randomID = ""
        (1..9).forEach {
            randomID += letter.random()
            if (it == 3 || it == 6)
                randomID += "-"
        }
        rectangle.setID(randomID)
    }

    private fun setRectanglePoint(sizeArray: Array<Int>, pointArray: Array<Int>) {
        val pointX = (0..(pointArray[0] - sizeArray[0])).random().toFloat()
        val pointY = (0..(pointArray[1] - sizeArray[1])).random().toFloat()
        rectangle.setPoint(pointX, pointY)
    }

    private fun setRectangleSize(sizeArray: Array<Int>) {
        val width = sizeArray[0]
        val height = sizeArray[1]
        rectangle.setSize(width, height)
    }

    private fun setRectangleColor() {
        val r = (0..255).random()
        val g = (0..255).random()
        val b = (0..255).random()
        rectangle.setColor(r, g, b)
    }

    private fun setRectangleAlpha() {
        val alpha = (0..255).random()
        rectangle.setAlpha(alpha)
    }

    private fun setRectangleText() {
        if (rectangle is TextRectangle) {
            val randomIndex = (0 until rectangle.getTextList().size).random()
            rectangle.setText(randomIndex)
        }
    }

    private fun setRectanglePhoto(photo: Bitmap?) {
        if (rectangle is PhotoRectangle) {
            photo?.let {
                rectangle.setBitmap(photo)
            }
        }
    }

    fun getInstance(sizeArray: Array<Int>, pointArray: Array<Int>, photo: Bitmap? = null): Rectangle {
        setRectangleID()
        setRectangleSize(sizeArray)
        setRectanglePoint(sizeArray, pointArray)
        setRectangleColor()
        setRectangleAlpha()
        setRectangleText()
        setRectanglePhoto(photo)
        return rectangle
    }

    override fun toString(): String {
        return "(${rectangle.id}), X:${rectangle.point[0]}, Y:${rectangle.point[0]}, W${rectangle.size[0]}, H${rectangle.size[1]}, R:${rectangle.color[0]}, G:${rectangle.color[1]}, B:${rectangle.color[2]}, Alpha: ${rectangle.alphaValue}"
    }

}
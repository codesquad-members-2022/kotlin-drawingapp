package com.codesquad_han.kotlin_drawingapp.model

import android.net.Uri
import com.codesquad_han.kotlin_drawingapp.model.property.BackgroundColor
import com.codesquad_han.kotlin_drawingapp.model.property.Point
import com.codesquad_han.kotlin_drawingapp.model.property.Size
import com.codesquad_han.kotlin_drawingapp.model.property.Transparency
import com.codesquad_han.kotlin_drawingapp.model.rectangle.ImageRectangle
import com.codesquad_han.kotlin_drawingapp.model.rectangle.NormalRectangle
import com.codesquad_han.kotlin_drawingapp.model.rectangle.TextRectangle

class RectangleFactory(x: Int, y: Int, wordList: List<String>) {
    private var x = x
    private var y = y
    private var wordList = wordList

    fun generateNormalRectangle(): NormalRectangle {
        val id = getRandomString(3) + "-" + getRandomString(3) + "-" + getRandomString(3)

        return NormalRectangle(
            id,
            Point((0..x).random(), (0..y).random()),
            Size(300, 240),  // Pixcel C API 31 에서 1dp = 2px, 따라서 2배해준 값 사용
            BackgroundColor((0..255).random(), (0..255).random(), (0..255).random()),
            Transparency((1..10).random()),
            null, -1, false, null
        )
    }

    fun generateTextRectangle(): TextRectangle {
        val id = getRandomString(3) + "-" + getRandomString(3) + "-" + getRandomString(3)

        return TextRectangle(
            id,
            Point((0..x).random(), (0..y).random()),
            Size(0, 0),
            BackgroundColor((0..255).random(), (0..255).random(), (0..255).random()),
            Transparency((1..10).random()),
            null, -1, false, getRandomText()
        )
    }

    fun generateImageRectangle(imageUri: Uri?): ImageRectangle {
        val id = getRandomString(3) + "-" + getRandomString(3) + "-" + getRandomString(3)

        return ImageRectangle(
            id,
            Point((0..x).random(), (0..y).random()),
            Size(300, 240),
            BackgroundColor((0..255).random(), (0..255).random(), (0..255).random()),
            Transparency((1..10).random()),
            imageUri, -1, false, null
        )
    }

    fun getRandomString(length: Int): String {
        val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return (1..length)
            .map { charset.random() }
            .joinToString("")
    }

    fun getRandomText(): String {
        var size = wordList.size
        var start = (0..size-5).random()
        return "${wordList[start]} ${wordList[start+1]} ${wordList[start+2]} ${wordList[start+3]} ${wordList[start+4]}"
    }
}
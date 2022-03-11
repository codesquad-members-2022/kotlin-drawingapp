package com.example.kotlindrawingapp.domain.figure

import android.graphics.Bitmap
import com.example.kotlindrawingapp.domain.figure.picture.Picture
import com.example.kotlindrawingapp.domain.figure.Alpha.Companion.generateAlpha
import com.example.kotlindrawingapp.domain.figure.ID.Companion.generateID
import com.example.kotlindrawingapp.domain.figure.Point.Companion.generatePoint
import com.example.kotlindrawingapp.domain.figure.RGB.Companion.generateRGB
import com.example.kotlindrawingapp.domain.figure.square.Square
import com.example.kotlindrawingapp.domain.figure.text.Text
import com.example.kotlindrawingapp.domain.figure.text.Text.Companion.generateText

object FigureFactory {

    fun createSquare(size: Size): Square {
        return Square(generateID(), generatePoint(), size, generateRGB(), generateAlpha())
    }

    fun createPicture(size: Size, bitmap: Bitmap): Picture {
        return Picture(
            generateID(),
            generatePoint(),
            size,
            null,
            generateAlpha(),
            Picture.bitmapToByteArray(bitmap)
        )
    }

    fun createText(size: Size): Text {
        return Text(
            generateID(),
            generatePoint(),
            size,
            generateRGB(),
            generateAlpha(),
            generateText()
        )
    }
}
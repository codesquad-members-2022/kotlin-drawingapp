package com.codesquad_han.kotlin_drawingapp.model.rectangle

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.net.Uri
import android.util.Log
import com.codesquad_han.kotlin_drawingapp.model.property.BackgroundColor
import com.codesquad_han.kotlin_drawingapp.model.property.Point
import com.codesquad_han.kotlin_drawingapp.model.property.Size
import com.codesquad_han.kotlin_drawingapp.model.property.Transparency

class TextRectangle(
    override val id: String,
    override var point: Point,
    override var size: Size,
    override var backgroundColor: BackgroundColor,
    override var transparency: Transparency,
    override var imageUri: Uri?,
    override var createNum: Int,
    var text: String?
) : BaseRectangle {

    var textSize = 70f

    override fun drawRectangle(context: Context, canvas: Canvas, isTemp: Boolean) {
        var paint = Paint().also {
            it.setColor(
                Color.argb(
                    this.transparency.transparency * 255 / 10,
                    this.backgroundColor.r,
                    this.backgroundColor.g,
                    this.backgroundColor.b
                )
            )
            it.textSize = textSize
        }

        if(isTemp){
            paint.alpha = 255 / 10 * 5
        }

        canvas.drawText(text!!, point.x.toFloat() + 10, point.y + textSize, paint)

        var rect = Rect()
        paint.getTextBounds(text!!, 0, text!!.length, rect)
        Log.d("AppTest", "text size : ${rect.width()}, ${rect.height()}")

        this.size.width = rect.width() + 30
        this.size.height = (textSize + textSize * 15 / 50).toInt()


    }

    override fun getTempRectangle(): TextRectangle {
        val textRectangleClone = TextRectangle(
            id,
            Point(point.x, point.y),
            Size(size.width, size.height),
            BackgroundColor(backgroundColor.r, backgroundColor.g, backgroundColor.b),
            Transparency(transparency.transparency),
            imageUri,
            createNum, text
        )

        return textRectangleClone
    }

    override fun getObjectName() = "Text $createNum"
}

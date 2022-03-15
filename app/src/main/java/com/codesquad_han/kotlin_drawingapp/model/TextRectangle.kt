package com.codesquad_han.kotlin_drawingapp.model

import android.content.Context
import android.graphics.Canvas
import android.net.Uri
import com.codesquad_han.kotlin_drawingapp.model.property.BackgroundColor
import com.codesquad_han.kotlin_drawingapp.model.property.Point
import com.codesquad_han.kotlin_drawingapp.model.property.Size
import com.codesquad_han.kotlin_drawingapp.model.property.Transparency

class TextRectangle(
    id: String,
    point: Point,
    size: Size,
    backgroundColor: BackgroundColor,
    transparency: Transparency,
    imageUri: Uri?,
    text: String?
) : BaseRectangle {
    override val id = id
    override var point = point
    override var size = size
    override var backgroundColor = backgroundColor
    override var transparency = transparency
    override var imageUri = imageUri
    var text = text


    override fun drawRectangle(context: Context, canvas: Canvas) {

    }

    override fun getTempRectangle(): TextRectangle {
        val textRectangleClone = TextRectangle(
            id,
            Point(point.x, point.y),
            Size(size.width, size.height),
            BackgroundColor(backgroundColor.r, backgroundColor.g, backgroundColor.b),
            Transparency(transparency.transparency),
            imageUri,
            text
        )

        return textRectangleClone
    }
}

package com.codesquad_han.kotlin_drawingapp.model.rectangle

import android.content.Context
import android.graphics.*
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import com.codesquad_han.kotlin_drawingapp.model.property.BackgroundColor
import com.codesquad_han.kotlin_drawingapp.model.property.Point
import com.codesquad_han.kotlin_drawingapp.model.property.Size
import com.codesquad_han.kotlin_drawingapp.model.property.Transparency


class NormalRectangle(
    override val id: String,
    override var point: Point,
    override var size: Size,
    override var backgroundColor: BackgroundColor,
    override var transparency: Transparency,
    override var imageUri: Uri?,
    text: String?
) : BaseRectangle {


    override fun toString(): String {
        return "($id), X:${point.x},Y:${point.y}, W:${size.width},H:${size.height}, R:${backgroundColor.r},G:${backgroundColor.g},${backgroundColor.b}, " +
                "Alpha:${transparency.transparency}, Image Uri : ${imageUri} ."
    }

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
        }

        if(isTemp){
            paint.alpha = 255 / 10 * 5
        }

        canvas.drawRect(
            this.point.x.toFloat(),
            this.point.y.toFloat(),
            (this.point.x + this.size.width).toFloat(),
            (this.point.y + this.size.height).toFloat(),
            paint
        )
    }


    override fun getTempRectangle(): NormalRectangle {
        val normalRectangleClone = NormalRectangle(
            id,
            Point(point.x, point.y),
            Size(size.width, size.height),
            BackgroundColor(backgroundColor.r, backgroundColor.g, backgroundColor.b),
            Transparency(transparency.transparency),
            imageUri,
            null
        )

        return normalRectangleClone
    }

}
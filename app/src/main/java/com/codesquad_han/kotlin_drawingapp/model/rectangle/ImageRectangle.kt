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


class ImageRectangle(
    override val id: String,
    override var point: Point,
    override var size: Size,
    override var backgroundColor: BackgroundColor,
    override var transparency: Transparency,
    override var imageUri: Uri?,
    override var createNum: Int,
    text: String?
) : BaseRectangle {

    override fun toString(): String {
        return "($id), X:${point.x},Y:${point.y}, W:${size.width},H:${size.height}, R:${backgroundColor.r},G:${backgroundColor.g},${backgroundColor.b}, " +
                "Alpha:${transparency.transparency}, Image Uri : $imageUri ."
    }

    override fun drawRectangle(context: Context, canvas: Canvas, isTemp: Boolean) {
        val bitmap: Bitmap = if (Build.VERSION.SDK_INT < 29) { //
            MediaStore.Images.Media.getBitmap(context.contentResolver, this.imageUri)
        } else {
            val source = ImageDecoder.createSource(context.contentResolver, this.imageUri!!)
            ImageDecoder.decodeBitmap(source)
        }

        val imagePaint = Paint()
        imagePaint.alpha = this.transparency.transparency * 255 / 10

        if(isTemp){
            imagePaint.alpha = 255 / 10 * 5
        }

        canvas.drawBitmap(
            bitmap, null, Rect(
                this.point.x,
                this.point.y,
                (this.point.x + this.size.width),
                (this.point.y + this.size.height)
            ), imagePaint
        )
    }

    override fun getTempRectangle(): ImageRectangle {
        return ImageRectangle(
            id,
            Point(point.x, point.y),
            Size(size.width, size.height),
            BackgroundColor(backgroundColor.r, backgroundColor.g, backgroundColor.b),
            Transparency(transparency.transparency),
            imageUri,
            createNum, null
        )
    }

    override fun getObjectName() = "Photo $createNum"
}
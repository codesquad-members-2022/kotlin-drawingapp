package com.codesquad_han.kotlin_drawingapp.model

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


    override fun toString(): String {
        return "($id), X:${point.x},Y:${point.y}, W:${size.width},H:${size.height}, R:${backgroundColor.r},G:${backgroundColor.g},${backgroundColor.b}, " +
                "Alpha:${transparency.transparency}, Image Uri : ${imageUri} ."
    }

    override fun drawRectangle(context: Context, canvas: Canvas) {
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

        // 이미지 uri 없으면 일반 사각형 그리기
        if (this.imageUri == null) {
            canvas.drawRect(
                this.point.x.toFloat(),
                this.point.y.toFloat(),
                (this.point.x + this.size.width).toFloat(),
                (this.point.y + this.size.height).toFloat(),
                paint
            )
        } else {   // 사각형에 할당된 이미지 uri가 있다면 그리도록 한다
            var bitmap: Bitmap
            if (Build.VERSION.SDK_INT < 29) { //
                bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, this.imageUri)
            } else {
                val source = ImageDecoder.createSource(context.contentResolver, this.imageUri!!)
                bitmap = ImageDecoder.decodeBitmap(source)
            }

            var imagePaint = Paint()
            imagePaint.alpha = this.transparency.transparency * 255 / 10
            canvas.drawBitmap(
                bitmap, null, Rect(
                    this.point.x,
                    this.point.y,
                    (this.point.x + this.size.width),
                    (this.point.y + this.size.height)
                ), imagePaint
            )
        }
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
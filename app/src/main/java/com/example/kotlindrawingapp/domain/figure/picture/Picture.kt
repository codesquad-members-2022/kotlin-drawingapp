package com.example.kotlindrawingapp.domain.figure.picture

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.kotlindrawingapp.domain.figure.Figure
import com.example.kotlindrawingapp.domain.figure.*
import java.io.ByteArrayOutputStream

data class Picture(
    override val id: ID,
    override var point: Point,
    override var size: Size,
    override var rgb: RGB?,
    override var alpha: Alpha,
    var memory: ByteArray
) : Figure() {

    companion object {
        fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
            return byteArrayOutputStream.toByteArray()
        }

        fun byteArrayToBitmap(memory: ByteArray): Bitmap {
            return BitmapFactory.decodeByteArray(memory, 0, memory.size)
        }
    }
}

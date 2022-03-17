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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Picture

        if (id != other.id) return false
        if (point != other.point) return false
        if (size != other.size) return false
        if (rgb != other.rgb) return false
        if (alpha != other.alpha) return false
        if (!memory.contentEquals(other.memory)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + point.hashCode()
        result = 31 * result + size.hashCode()
        result = 31 * result + (rgb?.hashCode() ?: 0)
        result = 31 * result + alpha.hashCode()
        result = 31 * result + memory.contentHashCode()
        return result
    }
}

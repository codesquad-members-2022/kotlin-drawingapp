package data

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

class ImageRectangle private constructor(
    override val id: String,
    private val image: Bitmap,
    override var rectangle: RectF,
    override var paint: Paint? = null,
    var clicked: Boolean = false
) : Rectangle {
    fun isOnTheRect(x: Float?, y: Float?): Boolean {
        val clickX: Float = x.let { x as Float }
        val clickY: Float = y.let { y as Float }
        if ((clickX in rectangle.left..rectangle.right) && (clickY in rectangle.top..rectangle.bottom)) {
            clicked = true
            return true
        }
        return false
    }

    override fun draw(canvas: Canvas?) {
        canvas?.drawBitmap(image, null, rectangle, null)
    }

    companion object Factory {
        fun makeRectangle(id: String, image: Bitmap, rectangles: RectF) =
            ImageRectangle(id, image, rectangles)
    }
}

package data

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

class RegularRectangle private constructor(
    override val id: String,
    override var rectangle: RectF,
    override var paint: Paint?,
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
        canvas?.drawRect(rectangle, paint!!)
    }

    companion object Factory {
        fun makeRectangle(id: String, rectangle: RectF, paint: Paint) =
            RegularRectangle(id, rectangle, paint)
    }
}

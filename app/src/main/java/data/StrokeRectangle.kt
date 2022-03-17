package data

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF

class StrokeRectangle private constructor(
    override val id: String,
    override var rectangle: RectF,
    override var paint: Paint? = Paint().apply {
        this.color = Color.BLUE
        this.style = Paint.Style.STROKE
        this.strokeWidth = 15F
    }
): Rectangle {
    override fun draw(canvas: Canvas?) {
        canvas?.drawRect(rectangle, paint!!)
    }

    companion object Factory {
        fun makeRectangle(id: String, rectangle: RectF) =
            StrokeRectangle(id, rectangle)
    }
}
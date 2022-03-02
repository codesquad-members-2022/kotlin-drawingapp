package Data

import Presenter.RandomRectGenerator
import android.graphics.Paint
import android.graphics.RectF

class RectFactory : RandomRectGenerator {
    override fun makeRandomRect(): Rectangles {
        val randomID = (65..128).random().toChar().toString()
        val x = (10..600).random().toFloat()
        val y = (90..500).random().toFloat()
        val w = 150.toFloat()
        val h = 120.toFloat()
        val r = (0..255).random()
        val g = (0..255).random()
        val b = (0..255).random()
        val a = (0..255).random()
        val paint = Paint()
        paint.setARGB(r, g, b, a)
        return Rectangles(randomID, RectF(x, y, w, h), paint)
    }
}
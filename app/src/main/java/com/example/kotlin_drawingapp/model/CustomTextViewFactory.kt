package com.example.kotlin_drawingapp.model

import android.graphics.Paint
import android.graphics.Rect
import android.util.Size
import com.example.kotlin_drawingapp.model.draw.DrawObject
import kotlin.random.Random

class CustomTextViewFactory : Factory() {
    companion object {
        private val text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Egestas tellus rutrum tellus pellentesque eu. Viverra justo nec ultrices dui sapien eget mi proin sed. Vel pretium lectus quam id leo. Molestie at elementum eu facilisis sed odio morbi quis commodo. Risus at ultrices mi tempus imperdiet nulla malesuada. In est ante in nibh mauris cursus mattis molestie a. Venenatis urna cursus eget nunc. Eget velit aliquet sagittis id consectetur purus ut. Amet consectetur adipiscing elit pellentesque habitant morbi tristique senectus. Consequat nisl vel pretium lectus quam id. Nisl vel pretium lectus quam id leo in vitae turpis. Purus faucibus ornare suspendisse sed. Amet mauris commodo quis imperdiet."
        private val textSplit = text.split(" ")

        private fun createRandomString(): String {
            val randomStart = Random.nextInt(0, textSplit.size - 5)
            val randomStringBuilder = StringBuilder()
            for (index in randomStart until randomStart + 5) {
                randomStringBuilder.append(textSplit[index])
                    .append(' ')
            }

            return randomStringBuilder.toString()
        }

        private fun getRandomEndPos(string: String): Int {
            return Random.nextInt(1, string.length)
        }

        private fun createPaint(textSize: Int, textColor: Color, alpha: Int): Paint {
            val paint = Paint()
            paint.isAntiAlias = true
            paint.textSize = textSize.toFloat()
            val newAlpha = (255 * (alpha / 10.0)).toInt()
            paint.color = android.graphics.Color.argb(newAlpha, textColor.r, textColor.g, textColor.b)
            return paint
        }

        private fun getTextViewSize(text: String, paint: Paint, endPos: Int): Size {
            val bounds = Rect()
            paint.getTextBounds(text, 0, endPos, bounds)

            return Size(bounds.width(), bounds.height())
        }

        fun create(): DrawObject.CustomText {
            val text = createRandomString()
            val endPos = getRandomEndPos(text)
            val alpha = randomAlpha()
            val paint = createPaint(Random.nextInt(50, 100), randomColor(), alpha)
            val textViewSize = getTextViewSize(text, paint, endPos)

            return DrawObject.CustomText(
                randomId(),
                textViewSize,
                randomPoint(),
                endPos,
                paint,
                text,
                alpha
            )
        }
    }
}
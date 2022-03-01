package com.example.kotlin_drawingapp.model

import android.content.Context
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.view.ViewCompat
import com.example.kotlin_drawingapp.R
import com.example.kotlin_drawingapp.shapemodel.Rectangle
import kotlin.math.roundToInt

private data class IvBindRect(val imageView: ImageView, val rect: Rectangle)

class Plane(private val context: Context, private val density: Float) {
    private val rectArray = mutableListOf<IvBindRect>()

    fun getCount(): Int {
        return rectArray.size
    }

    fun create(rect: Rectangle): ImageView {
        val imageView = ImageView(context)
        val param = FrameLayout.LayoutParams(
            convertDpToPx(rect.size.width),
            convertDpToPx(rect.size.height)
        )
        param.setMargins(convertDpToPx(rect.point.x), convertDpToPx(rect.point.y), 0, 0)
        imageView.layoutParams = param
        imageView.alpha = (rect.alpha / 10.0).toFloat()
        imageView.setBackgroundColor(rect.rgb.getRgb())
        imageView.id = ViewCompat.generateViewId()
        rectArray.add(IvBindRect(imageView, rect))

        return imageView
    }

    private fun convertDpToPx(dp: Int): Int {
        return (dp * density).roundToInt()
    }

    private fun convertPxToDp(px: Float): Int {
        return (px / density).roundToInt()
    }

    fun getRectangleByIndex(index: Int): Rectangle? {
        if (index < 0 || index >= rectArray.size) {
            return null
        }
        return rectArray[index].rect
    }

    fun getRectangleByPosition(x: Float, y: Float): Rectangle? {
        val index = getRectangleIndex(x, y)
        return if (index == -1) {
            null
        } else {
            rectArray[index].rect
        }
    }

    private fun getRectangleIndex(x: Float, y: Float): Int {
        val convertX = convertPxToDp(x)
        val convertY = convertPxToDp(y)

        for (i in rectArray.size - 1 downTo 0) {
            if (rectArray[i].rect.point.x <= convertX && rectArray[i].rect.point.x + rectArray[i].rect.size.width >= convertX) {
                if (rectArray[i].rect.point.y <= convertY && rectArray[i].rect.point.y + rectArray[i].rect.size.height >= convertY) {
                    return i
                }
            }
        }

        return -1
    }

    fun getRectangleImageByPosition(x: Float, y: Float): ImageView? {
        val index = getRectangleIndex(x, y)
        return if (index == -1) {
            null
        } else {
            rectArray[index].imageView
        }
    }

    fun getSelectRectangleBorder(x: Float, y: Float): ImageView? {
        val rect = getRectangleByPosition(x, y)
        var border: ImageView? = null
        border = rect?.let {
            val imageView = ImageView(context)
            val param = FrameLayout.LayoutParams(
                convertDpToPx(rect.size.width),
                convertDpToPx(rect.size.height)
            )
            param.setMargins(convertDpToPx(rect.point.x), convertDpToPx(rect.point.y), 0, 0)
            imageView.layoutParams = param
            imageView.setBackgroundResource(R.drawable.imageview_border)
            imageView.id = ViewCompat.generateViewId()
            imageView
        }

        return border
    }
}

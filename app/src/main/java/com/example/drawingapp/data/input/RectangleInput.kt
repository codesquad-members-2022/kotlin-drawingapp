package com.example.drawingapp.data.input

import android.graphics.Point
import android.graphics.Rect
import com.example.drawingapp.InputFactory
import com.example.drawingapp.data.attribute.RectangleSize
import com.example.drawingapp.util.generateRandom
import kotlin.random.Random

class RectangleInput(
    override val count: Int,
    override val randomId: String = generateRandom(),
    override val pointX: Int = Random.nextInt(2300),
    override val pointY: Int = Random.nextInt(1300),
    override val colorR: Int = Random.nextInt(255),
    override val colorG: Int = Random.nextInt(255),
    override val colorB: Int = Random.nextInt(255),
    override val alpha: Int = Random.nextInt(10)
) : InputFactory {

    override fun getRect(): Rect {
        val point = getPoints(Point(pointX, pointY), RectangleSize())
        return Rect(point[0], point[1], point[2], point[3])
    }

    private fun getPoints(
        point: Point,
        size: RectangleSize
    ): IntArray {
        val start = getStart(point.x, size.width)
        val end = getEnd(point.x, size.width)
        val top = getTop(point.y, size.height)
        val bottom = getBottom(point.y, size.height)
        return intArrayOf(start, top, end, bottom)
    }

    private fun getStart(
        x: Int,
        width: Int
    ) = x - (width / 2)

    private fun getEnd(
        x: Int,
        width: Int
    ) = x + (width / 2)

    private fun getTop(
        y: Int,
        height: Int
    ) = y + (height / 2)

    private fun getBottom(
        y: Int,
        height: Int
    ) = y - (height / 2)
}
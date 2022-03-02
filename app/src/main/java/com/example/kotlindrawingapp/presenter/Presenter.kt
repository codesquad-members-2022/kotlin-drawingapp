package com.example.kotlindrawingapp.presenter

import com.example.kotlindrawingapp.CustomCanvas
import com.example.kotlindrawingapp.plane.Plane
import com.example.kotlindrawingapp.square.*

class Presenter(
    private val view: Contract.View
) : Contract.Presenter {

    private var index = 0
    private val canvasList = mutableListOf<CustomCanvas>()
    private val squares = listOf<Square>(
        SquareFactory.createSquare(Point(10, 200), Size(), RGB(245, 0, 245), Alpha(9)),
        SquareFactory.createSquare(Point(110, 180), Size(), RGB(43, 124, 95), Alpha(5)),
        SquareFactory.createSquare(Point(590, 90), Size(), RGB(98, 244, 15), Alpha(7)),
        SquareFactory.createSquare(Point(330, 450), Size(), RGB(125, 39, 99), Alpha(1))
    )
    private val plane = Plane.of(listOf())

    override fun drawRectangle(canvas: CustomCanvas) {
        canvasList.add(canvas)
        val square = squares[index++]
        plane.addSquare(square)
        canvas.drawRectangle(square)
    }

    override fun selectRectangle(x: Float, y: Float) {
        val idx = plane.contain(x, y - 185)
        deselect()
        if (idx != -1) {
            deselect()
            canvasList[idx].selectRectangle(squares[idx])
            val rgb = squares[idx].rgb.decimalToHex()
            view.updateBoard(rgb, squares[idx].alpha.alpha)
        }
    }

    private fun deselect() {
        canvasList.forEachIndexed { index, canvas -> canvas.removeStroke(squares[index]) }
    }
}
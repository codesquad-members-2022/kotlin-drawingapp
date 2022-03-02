package com.codesquad_han.kotlin_drawingapp.rectangle

import com.codesquad_han.kotlin_drawingapp.model.Plane

class RectanglePresenter(val plane: Plane, val rectangleView: RectangleContract.View) :
    RectangleContract.Presenter {

    init {
        rectangleView.presenter = this
    }

    override fun start() {
        generateRectangle()
    }

    override fun generateRectangle() {
        val rectangle = plane.generateRectangle()

        val red = rectangle.getBackgroundColor().r
        val green = rectangle.getBackgroundColor().g
        val blue = rectangle.getBackgroundColor().b
        val transparency = rectangle.getTransparency().transparency

        rectangleView.showRectangle(
            rectangle.getSize().width,
            rectangle.getSize().height,
            rectangle.getPoint().x,
            rectangle.getPoint().y,
            getColorString(transparency, red, green, blue)
        )
    }

    fun getColorString(alpha: Int, r: Int, g: Int, b: Int): String {  // 수정하기 형식에 안맞게 나오는 경우도 존재!!
        var red = Integer.toHexString(r)
        var green = Integer.toHexString(g)
        var blue = Integer.toHexString(b)

        if(red.length == 1) red = "0" + red
        if(green.length == 1) green = "0" + green
        if(blue.length == 1) blue = "0" + blue
        
        return "${red}${green}${blue}"
    }

}
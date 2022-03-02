package com.codesquad_han.kotlin_drawingapp.rectangle

import android.util.Log
import android.widget.ImageView
import com.codesquad_han.kotlin_drawingapp.model.Plane
import com.codesquad_han.kotlin_drawingapp.model.RectangleImageviewData

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
        var alphaStr = ""
        when(alpha){ // 투명도 범위 10개로 나눔
            0 -> alphaStr = "00"
            1 -> alphaStr = "1A"
            2 -> alphaStr = "33"
            3 -> alphaStr = "4D"
            4 -> alphaStr = "66"
            5 -> alphaStr = "80"
            6 -> alphaStr = "99"
            7 -> alphaStr = "B3"
            8 -> alphaStr = "CC"
            9 -> alphaStr = "E6"
            10 -> alphaStr = "FF"
        }

        var red = Integer.toHexString(r)
        var green = Integer.toHexString(g)
        var blue = Integer.toHexString(b)

        if(red.length == 1) red = "0" + red
        if(green.length == 1) green = "0" + green
        if(blue.length == 1) blue = "0" + blue

        Log.d("AppTest", "${alphaStr}${red}${green}${blue}")
        return "${alphaStr}${red}${green}${blue}"
    }

    override fun saveImageView(imageView: ImageView) {
        plane.saveImageView(imageView)
    }

    override fun selectRectangleImageView(imageView: ImageView) {
        getRectangleImageViewList(plane.selectImageView(imageView))
    }

    override fun getRectangleImageViewList(rectangleList: ArrayList<RectangleImageviewData>) {
        rectangleView.showSelectedRectangle(rectangleList)
    }

}
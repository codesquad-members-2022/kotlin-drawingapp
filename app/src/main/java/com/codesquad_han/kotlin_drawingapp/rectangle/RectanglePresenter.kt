package com.codesquad_han.kotlin_drawingapp.rectangle

import android.util.Log
import com.codesquad_han.kotlin_drawingapp.data.RectangleRepository
import com.codesquad_han.kotlin_drawingapp.model.Rectangle

class RectanglePresenter(val rectangleRepository: RectangleRepository, val rectangleView: RectangleContract.View) :
    RectangleContract.Presenter {

    init {
        rectangleView.presenter = this
    }

    override fun start() {
        addRectangle()
    }

    override fun addRectangle() {  // 사각형 추가만 하기
        rectangleRepository.addRectangle()
    }

    override fun getRectangleList(){ // 사각형 추가한 리스트 전달
        rectangleView.showRectangle(rectangleRepository.getRectangleList())
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



}
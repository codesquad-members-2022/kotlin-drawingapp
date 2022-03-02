package com.codesquad_han.kotlin_drawingapp.rectangle

import com.codesquad_han.kotlin_drawingapp.BasePresenter
import com.codesquad_han.kotlin_drawingapp.BaseView

interface RectangleContract {

    interface View : BaseView<BasePresenter> {
        fun showRectangle(width:Int, height: Int, x:Int, y:Int, colorStr: String)   // 사각형 정보 받아와서 이미지뷰 만들기
    }

    interface Presenter : BasePresenter {
        fun generateRectangle() // 사각형 생성
    }
}
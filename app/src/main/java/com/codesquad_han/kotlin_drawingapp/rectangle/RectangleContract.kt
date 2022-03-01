package com.codesquad_han.kotlin_drawingapp.rectangle

import com.codesquad_han.kotlin_drawingapp.BasePresenter
import com.codesquad_han.kotlin_drawingapp.BaseView

interface RectangleContract {

    interface View : BaseView<BasePresenter> {
        fun showRectangle()   // 사각형 정보 받아와서 이미지뷰 만들기
    }

    interface Presenter : BasePresenter {

    }
}